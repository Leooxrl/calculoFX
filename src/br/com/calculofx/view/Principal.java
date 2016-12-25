package br.com.calculofx.view;

import java.math.BigDecimal;
import java.util.Map;

import br.com.calculo.regraNegocio.CalculoException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Principal extends Application {

    GridPane grid;
    private Stage stage;

    private Text txtTitulo, txtSubtitulo, txtTitulo1, txtTexto, txtInfo, txtResultado;

    private ImageView img;

    private TextField txLargura;
    private TextField txComprimento;
    private TextField txUndMedida;

    private Label lbLargura, lbComprimento, lbUnMedida;

    private Button btCalcula;
    private Button btLimpa;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primayStage) throws Exception {
        initComponents();
        initLayout();
        Scene scene = new Scene(grid, 800, 800);
        scene.getStylesheets().add("calculo.css");
        primayStage.setScene(scene);
        primayStage.setResizable(false);
        primayStage.setTitle("Sou Engenheiro");
        primayStage.show();
        initListeners();
        this.stage = primayStage;
    }

    private void initComponents() {
        grid = new GridPane();

        txtTitulo = new Text("Sou Engenheiro");

        txtSubtitulo = new Text(
                        "Resolve problemas de engenharia por meio do cálculo diferencial e integral.");
        txtTitulo1 = new Text("Aplicação de derivada para determinação de máximos e mínimos");
        txtTexto = new Text(
                        "Para formar uma caixa de papelão sem tampa, cortam-se quadrados identicos dos cantos da folha de papelão\ne dobram-se as abas resultantes, conforme ilustra a figura 1.");

        txtInfo = new Text(
                        "Informe as dimensões da folha de papelão e o aplicativo irá calcular a medida que os quadrados devem\nser cortados para se obter uma caixa de maior volume possível.");

        txtResultado = new Text();

        img = new ImageView(new Image("caixa.bmp"));

        txLargura = new TextField();
        txComprimento = new TextField();
        txUndMedida = new TextField();

        lbLargura = new Label("Largura da folha");
        lbComprimento = new Label("Comprimento da folha");
        lbUnMedida = new Label("Unidade de medida");

        btCalcula = new Button("Calcular");
        btLimpa = new Button("Limpar");
    }

    private void initLayout() {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("grid");

        txtTitulo.getStyleClass().add("h1");
        grid.add(txtTitulo, 0, 0, 2, 1);

        txtSubtitulo.getStyleClass().add("h2");
        grid.add(txtSubtitulo, 0, 1);

        txtTitulo1.getStyleClass().add("h3");
        grid.add(txtTitulo1, 0, 4);

        grid.add(txtTexto, 0, 5);

        grid.add(img, 0, 6);

        grid.add(txtInfo, 0, 7);

        grid.add(lbLargura, 0, 8);
        grid.add(txLargura, 0, 9);

        grid.add(lbComprimento, 0, 10);
        grid.add(txComprimento, 0, 11);

        grid.add(lbUnMedida, 0, 12);
        grid.add(txUndMedida, 0, 13);

        grid.add(btCalcula, 0, 14);
        grid.add(btLimpa, 0, 15);

        grid.add(txtResultado, 0, 17);
    }

    private void initListeners() {
        btCalcula.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                BigDecimal largura = new BigDecimal(txLargura.getText());
                BigDecimal comprimento = new BigDecimal(txComprimento.getText());

                try {
                    Map<String, BigDecimal> solucoes =
                                    br.com.calculo.main.Principal.calcular(largura, comprimento);

                    String undMedida = txUndMedida.getText();
                    txtResultado.setText("O maior volume possível da caixa é: "
                                    + solucoes.get("volume") + undMedida + "³");

                    txtResultado.setText(txtResultado.getText()
                                    + "\nPara atingir este volume os quadrados devem ser cortados com medidas: "
                                    + solucoes.get("quadrado") + undMedida);

                    txtResultado.setText(
                                    txtResultado.getText() + "\nAs dimensões da caixa serão:\n");

                    txtResultado.setText(txtResultado.getText() + "\nAltura: "
                                    + solucoes.get("altura") + undMedida);
                    txtResultado.setText(txtResultado.getText() + "\nLargura: "
                                    + solucoes.get("largura") + undMedida);
                    txtResultado.setText(txtResultado.getText() + "\nComprimento: "
                                    + solucoes.get("comprimento") + undMedida);

                } catch (CalculoException e) {

                    e.printStackTrace();
                }

            }

        });

        btLimpa.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                txtResultado.setText("");
                txLargura.setText("");
                txComprimento.setText("");
                txUndMedida.setText("");
            }
        });

    }

}
