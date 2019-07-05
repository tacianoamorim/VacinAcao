package br.ufrpe.vacinacao.gui.campanha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FrmCadastroCampanha {
	@FXML
    private Label vacinaLabel;

    @FXML
    private Label campanhaLabel;

    @FXML
    private Label valorGastoLabel;

    @FXML
    private Label dataInicioDivulgacaoLabel;

    @FXML
    private Label dataFimDivulgacaoLabel;

    @FXML
    private Label dataInicioExecucaoLabel;

    @FXML
    private TextField vacinaText;

    @FXML
    private TextField campanhaText;

    @FXML
    private TextField valorTotalText;

    @FXML
    private DatePicker dataInicioDivulgacaoDate;

    @FXML
    private DatePicker dataFimDivulgacaoDate;

    @FXML
    private DatePicker dataInicioExecucaoDate;

    @FXML
    private DatePicker dataFimExecucaoDate;

    @FXML
    private Button cadastrarButton;
    
    @FXML
    private TextArea observacaoTextArea;

    @FXML
    private Label observacaoLabel;
    
    @FXML
    void cadastrarButtonClick(ActionEvent event) {

    }

}
