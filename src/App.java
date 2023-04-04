import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

	public static void main(String[] args) throws Exception {
		// conexao http, chamando os top filmes no no json
		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
		URI endereco = URI.create(url);
		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());
		String body = response.body();
		System.out.println(body);

		// parseando os dados que interessam (titulo, capa do filme e classificação)
		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);

		// criando diretório de figurinhas
		File directory = new File("stickers/");
		directory.mkdir();

		// exibição e manipulação dos dados
		stickerGenerator stickerGenerator = new stickerGenerator();
		for (Map<String, String> filme : listaDeFilmes) {

			String imageUrl = filme.get("image");
			String title = filme.get("title");
			InputStream inputStream = new URL(imageUrl).openStream();


			String fileName = "stickers/" + title + ".png";

			stickerGenerator.create(inputStream, fileName);

			System.out.println(title);
			System.out.println();

			// System.out.println("\u001b[1m\u001B[36mTítulo do filme:\u001b[m " + filme.get("title"));
			// System.out.println(filme.get("image"));
			// System.out.println("\u001b[1m\u001b[47mRanking:\u001b[m " + filme.get("rank"));
			// System.out.println(filme.get("imDbRating"));
			// double classificacao = Double.parseDouble(filme.get("imDbRating"));
			// int numeroFoguinhos = (int) classificacao;
			// for (int e = 1; e <= numeroFoguinhos; e++) {
			// 	System.out.print("🔥");
			// }
			// System.out.println("\n");
		}
	}

}
