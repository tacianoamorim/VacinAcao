package br.ufrpe.vacinacao.gui.campanha;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.sql.Date;

import br.ufrpe.vacinacao.negocio.entidade.Campanha;
import br.ufrpe.vacinacao.repositorio.CampanhaDAO;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class FrmCadastroCampanha {
	
	private Campanha campanha;
	private CampanhaDAO controladorCampanha;
	
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
    	campanha = new Campanha();
    	
    	campanha.setNome(campanhaText.getText());
    	campanha.setVacina(Integer.parseInt(vacinaText.getText()));
    	campanha.setDataInicioDivulgacao(Date.valueOf(dataInicioDivulgacaoDate.getValue()));
    	campanha.setDataFimDivulgacao(Date.valueOf(dataFimDivulgacaoDate.getValue()));
    	campanha.setDataInicioExecucao(Date.valueOf(dataInicioExecucaoDate.getValue()));
    	campanha.setDataFimExecucao(Date.valueOf(dataFimExecucaoDate.getValue()));
    	campanha.setObservacao(observacaoTextArea.getText());
    	controladorCampanha.insert(campanha);
    }

}
