package br.com.fiap.view;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import br.com.fiap.bo.ClienteBOStub;
import br.com.fiap.bo.ClienteBOStub.Buscar;
import br.com.fiap.bo.ClienteBOStub.BuscarResponse;
import br.com.fiap.bo.ClienteBOStub.Cadastrar;
import br.com.fiap.bo.ClienteBOStub.Cliente;

public class Tela {

	protected Shell shlClienteCadastro;
	private Text textNome;
	private Text textSobrenome;
	private Text textRg;
	private Text textCpf;
	private Text textEndereco;
	private Text textCodigo;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Tela window = new Tela();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlClienteCadastro.open();
		shlClienteCadastro.layout();
		while (!shlClienteCadastro.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlClienteCadastro = new Shell();
		shlClienteCadastro.setSize(700, 450);
		shlClienteCadastro.setText("Cliente Cadastro");
		
		Label lblCadastro = new Label(shlClienteCadastro, SWT.NONE);
		lblCadastro.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.NORMAL));
		lblCadastro.setBounds(131, 28, 78, 28);
		lblCadastro.setText("Cadastro");
		
		Label lblNome = new Label(shlClienteCadastro, SWT.NONE);
		lblNome.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblNome.setBounds(50, 90, 43, 21);
		lblNome.setText("Nome");
		
		textNome = new Text(shlClienteCadastro, SWT.BORDER);
		textNome.setBounds(99, 90, 187, 21);
		
		Label lblSobrenome = new Label(shlClienteCadastro, SWT.NONE);
		lblSobrenome.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblSobrenome.setBounds(50, 135, 81, 21);
		lblSobrenome.setText("Sobrenome");
		
		textSobrenome = new Text(shlClienteCadastro, SWT.BORDER);
		textSobrenome.setBounds(137, 135, 149, 21);
		
		Label lblDataDeNascimento = new Label(shlClienteCadastro, SWT.NONE);
		lblDataDeNascimento.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblDataDeNascimento.setBounds(50, 178, 140, 21);
		lblDataDeNascimento.setText("Data de Nascimento");
		
		Label lblRg = new Label(shlClienteCadastro, SWT.NONE);
		lblRg.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblRg.setBounds(50, 222, 21, 21);
		lblRg.setText("RG");
		
		textRg = new Text(shlClienteCadastro, SWT.BORDER);
		textRg.setBounds(77, 224, 209, 21);
		
		Label lblCpf = new Label(shlClienteCadastro, SWT.NONE);
		lblCpf.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblCpf.setBounds(50, 273, 27, 21);
		lblCpf.setText("CPF");
		
		textCpf = new Text(shlClienteCadastro, SWT.BORDER);
		textCpf.setBounds(93, 275, 193, 21);
		
		Label lblEndereo = new Label(shlClienteCadastro, SWT.NONE);
		lblEndereo.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblEndereo.setBounds(50, 309, 64, 21);
		lblEndereo.setText("Endere\u00E7o");
		
		textEndereco = new Text(shlClienteCadastro, SWT.BORDER);
		textEndereco.setBounds(120, 309, 166, 21);
		
		Label label = new Label(shlClienteCadastro, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(337, 72, 2, 286);
		
		Button btnNewButton = new Button(shlClienteCadastro, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				String nome = textNome.getText();
				String sobrenome = textSobrenome.getText();
				String rg = textRg.getText();
				String cpf = textCpf.getText();
				Calendar dataNasc = new GregorianCalendar();
				String endereco = textEndereco.getText();
				
				Cliente c = new Cliente();
				c.setNome(nome);
				c.setSobrenome(sobrenome);
				c.setRg(rg);
				c.setCpf(cpf);
				c.setDataNascimento(dataNasc);
				c.setEndereco(endereco);
				
				
				try {
					ClienteBOStub stub = new ClienteBOStub();
					
					Cadastrar params = new Cadastrar();
					params.setCliente(c);
					stub.cadastrar(params);
					
					MessageBox mb = new MessageBox(shlClienteCadastro, SWT.OK);
					mb.setMessage("Cliente cadastrado!");
					mb.open();
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(211, 350, 75, 25);
		btnNewButton.setText("Cadastrar");
		
		Label lblBusca = new Label(shlClienteCadastro, SWT.NONE);
		lblBusca.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.NORMAL));
		lblBusca.setBounds(491, 28, 49, 28);
		lblBusca.setText("Busca");
		
		Label lblDigiteOCdigo = new Label(shlClienteCadastro, SWT.NONE);
		lblDigiteOCdigo.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblDigiteOCdigo.setBounds(391, 90, 105, 21);
		lblDigiteOCdigo.setText("Digite o c\u00F3digo");
		
		textCodigo = new Text(shlClienteCadastro, SWT.BORDER);
		textCodigo.setBounds(391, 117, 105, 21);
		
		DateTime dateTime = new DateTime(shlClienteCadastro, SWT.BORDER);
		dateTime.setBounds(196, 175, 90, 24);
		
		Label labelNome = new Label(shlClienteCadastro, SWT.BORDER);
		labelNome.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		labelNome.setEnabled(false);
		labelNome.setBounds(391, 158, 149, 21);
		
		Label labelSobrenome = new Label(shlClienteCadastro, SWT.BORDER);
		labelSobrenome.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		labelSobrenome.setEnabled(false);
		labelSobrenome.setBounds(391, 197, 149, 21);
		
		Label labelRg = new Label(shlClienteCadastro, SWT.BORDER);
		labelRg.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		labelRg.setEnabled(false);
		labelRg.setBounds(391, 235, 149, 21);
		
		Label labelCpf = new Label(shlClienteCadastro, SWT.BORDER);
		labelCpf.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		labelCpf.setEnabled(false);
		labelCpf.setBounds(391, 272, 149, 21);
		
		Label labelEnredeco = new Label(shlClienteCadastro, SWT.BORDER);
		labelEnredeco.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		labelEnredeco.setEnabled(false);
		labelEnredeco.setBounds(391, 308, 149, 21);
		
		Button btnBuscar = new Button(shlClienteCadastro, SWT.NONE);
		btnBuscar.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnBuscar.addSelectionListener(new SelectionListener() {			
			@Override
			public void widgetSelected(SelectionEvent e) {
			
				try {
					ClienteBOStub stub = new ClienteBOStub();
					Buscar params = new Buscar();
					int codigo = Integer.parseInt(textCodigo.getText());
					
					params.setCodigo(codigo);
					BuscarResponse response = stub.buscar(params);
					Cliente c = response.get_return();
					if (c != null) {
						labelNome.setText(c.getNome());
						labelSobrenome.setText(c.getSobrenome());
						labelRg.setText(c.getRg());
						labelCpf.setText(c.getCpf());
						labelEnredeco.setText(c.getEndereco());						
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnBuscar.setBounds(565, 113, 75, 25);
		btnBuscar.setText("Buscar");
		shlClienteCadastro.setTabList(new Control[]{textNome, textSobrenome, textRg, textCpf, textEndereco, btnNewButton, textCodigo, dateTime, btnBuscar});

	}
}
