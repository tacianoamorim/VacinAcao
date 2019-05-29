package br.ufrpe.spjc.gui;

public class Main {

	public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            try {
            	//cargaInicial();
            	FrmLogin window= new FrmLogin();
            	window.setVisible(true);
//				FrmLogin window = new FrmLogin();
//				window.setVisible(true);
				
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }); 
    }

//	private static void cargaInicial() {
//		
//		// Professor
//		Professor professor01= new Professor(0, "Marx Paulo", new Date(), "marx", "123","1 Prof");
//		Fachada.getInstance().inserir(professor01);
//		Professor professor02= new Professor(0, "Maria Eduarda", new Date(), "maria", "123","2 Prof");
//		Fachada.getInstance().inserir(professor02);
//		Professor professor03= new Professor(0, "Paulo Mello", new Date(), "paulo", "123","3 Prof");
//		Fachada.getInstance().inserir(professor03);
//		
//		// Disciplina
//		Disciplina disciplina01= new Disciplina(0, "Matem�tica", "1+2+3+4");
//		Fachada.getInstance().inserir(disciplina01);
//		Disciplina disciplina02= new Disciplina(0, "Portugues", "blablabla");
//		Fachada.getInstance().inserir(disciplina02);
//		Disciplina disciplina03= new Disciplina(0, "Ciencias", "Lua e sol");
//		Fachada.getInstance().inserir(disciplina03);
//		Disciplina disciplina04= new Disciplina(0, "Geografia", "Norte e sul");
//		Fachada.getInstance().inserir(disciplina04);
//		Disciplina disciplina05= new Disciplina(0, "Fisica", "Velocidade");
//		Fachada.getInstance().inserir(disciplina05);
//		Disciplina disciplina06= new Disciplina(0, "Quimica", "Materia");
//		Fachada.getInstance().inserir(disciplina06);
//		
//		// Turmas
//		Turma turma01= new Turma(0, disciplina01, professor03, 40);
//		Fachada.getInstance().inserir(turma01);
//		Turma turma02= new Turma(0, disciplina02, professor03, 40);
//		Fachada.getInstance().inserir(turma02);
//		Turma turma03= new Turma(0, disciplina03, professor03, 40);
//		Fachada.getInstance().inserir(turma03);
//		Turma turma04= new Turma(0, disciplina04, professor01, 40);
//		Fachada.getInstance().inserir(turma04);	
//		Turma turma05= new Turma(0, disciplina05, professor02, 40);
//		Fachada.getInstance().inserir(turma05);		
//		Turma turma06= new Turma(0, disciplina06, professor02, 40);
//		Fachada.getInstance().inserir(turma06);	
//		Turma turma07= new Turma(0, disciplina05, professor01, 40);
//		Fachada.getInstance().inserir(turma07);	
//		
//		// Aluno
//		Aluno aluno01= new Aluno(0, "Andre da Silva", new Date(), "andre", "123", 1);
//		Fachada.getInstance().inserir(aluno01);
//		Aluno aluno02= new Aluno(0, "Maria Clara", new Date(), "maria", "123", 2);
//		Fachada.getInstance().inserir(aluno02);	
//		Aluno aluno03= new Aluno(0, "Paulo Marques", new Date(), "paulo", "123", 3);
//		Fachada.getInstance().inserir(aluno03);			
//		Aluno aluno04= new Aluno(0, "Juliana Dias", new Date(), "dias", "123", 4);
//		Fachada.getInstance().inserir(aluno04);	
//		Aluno aluno05= new Aluno(0, "Hugo alexandre", new Date(), "hugo", "123", 4);
//		Fachada.getInstance().inserir(aluno05);	
//		
//		// RendimentoEscolar
//		RendimentoEscolar rendEscolar01= new RendimentoEscolar(0, turma01, aluno01);
//		Fachada.getInstance().inserir(rendEscolar01);
//		RendimentoEscolar rendEscolar02= new RendimentoEscolar(0, turma01, aluno02);
//		Fachada.getInstance().inserir(rendEscolar02);
//		RendimentoEscolar rendEscolar03= new RendimentoEscolar(0, turma02, aluno01);
//		Fachada.getInstance().inserir(rendEscolar03);
//		RendimentoEscolar rendEscolar04= new RendimentoEscolar(0, turma03, aluno01);
//		Fachada.getInstance().inserir(rendEscolar04);
//		RendimentoEscolar rendEscolar05= new RendimentoEscolar(0, turma03, aluno02);
//		Fachada.getInstance().inserir(rendEscolar05);
//		RendimentoEscolar rendEscolar06= new RendimentoEscolar(0, turma03, aluno03);
//		Fachada.getInstance().inserir(rendEscolar06);		
//		RendimentoEscolar rendEscolar07= new RendimentoEscolar(0, turma03, aluno04);
//		Fachada.getInstance().inserir(rendEscolar07);		
//		RendimentoEscolar rendEscolar08= new RendimentoEscolar(0, turma01, aluno04);
//		Fachada.getInstance().inserir(rendEscolar08);	
//		RendimentoEscolar rendEscolar09= new RendimentoEscolar(0, turma01, aluno03);
//		Fachada.getInstance().inserir(rendEscolar09);			
//	}	
}