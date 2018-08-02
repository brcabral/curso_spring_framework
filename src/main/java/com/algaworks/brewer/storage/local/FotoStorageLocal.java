package com.algaworks.brewer.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.brewer.storage.FotoStorage;

public class FotoStorageLocal implements FotoStorage {

	private static final Logger LOGGER = LoggerFactory.getLogger(FotoStorageLocal.class);
	private Path local;
	private Path localTemporario;

	public FotoStorageLocal() {
		// Linux e MAC
		// this.local = getDefault().getPath(System.getenv("HOME"), ".brewerfotos");

		// Windows
		// this.local = getDefault().getPath(System.getProperty("user.home"),
		// ".brewerfotos");

		this.local = getDefault().getPath("C:\\Users\\oliveirb\\Downloads\\curso-spring", "brewerfotos");
		criarPastas();
	}

	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localTemporario = getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Diretório criado para salvar fotos.");
				LOGGER.debug("Diretório principal: " + this.local.toAbsolutePath());
				LOGGER.debug("Diretório temporário: " + this.localTemporario.toAbsolutePath());
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro criando pasta para salvar foto.", e);
		}
	}

	@Override
	public String salvarTemporariamente(MultipartFile[] files) {
		String novoNome = null;

		if ((files != null) && (files.length > 0)) {
			MultipartFile arquivo = files[0];
			novoNome = renomearArquivo(arquivo.getOriginalFilename());

			try {
				arquivo.transferTo(
						new File(this.localTemporario.toAbsolutePath() + getDefault().getSeparator() + novoNome));
			} catch (IOException e) {
				throw new RuntimeException("Erro ao salvar a foto na pasta temporária.", e);
			}
		}

		return novoNome;
	}

	@Override
	public void salvar(String foto) {
		try {
			Files.move(this.localTemporario.resolve(foto), this.local.resolve(foto));
		} catch (IOException e) {
			throw new RuntimeException("Erro movendo a foto para destino final", e);
		}
	}

	private String renomearArquivo(String nomeOriginal) {
		String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Nome original: %s. Novo novo: %s", nomeOriginal, novoNome);
		}

		return novoNome;
	}

	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro lendo a foto temporária", e);
		}
	}
}
