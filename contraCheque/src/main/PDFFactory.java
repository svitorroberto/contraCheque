package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;

import managedBeans.InputTextAreaMB;
import model.Caminhos;
import model.Funcionario;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFFactory {
			
	public static String sufixo;
	public static String data;

	public void fabricarPdf(Funcionario f) {
		
	        Document doc = new Document(PageSize.A4, 0, 0, 2, 0);
			
	        //PEGA INFORMAÇÕES DA DATA DO CONTRACHEQUE
			String array[] = f.getReferencia().split("/");
			String mes = array[0];
			String ano = array[1].trim();
			System.out.println(mes);
			System.out.println(ano);
			
			//CRIA NOVO DIRETÓRIO PARA ARMAZENAR PDFs
			String nomeDiretorio = null;
			data = mes+"-"+ano;
			sufixo = "contracheque_"+data;
		    try {
		    	nomeDiretorio = Caminhos.dirCriacaoPDF + sufixo;   
		         if (!Paths.get(nomeDiretorio).toFile().exists()) { // Verifica se o diretório existe.   
		             (new File(nomeDiretorio)).mkdir();   // Cria o diretório   
		         }   
		    } catch (Exception ex) {   
		         JOptionPane.showMessageDialog(null,"Err","Erro ao criar o diretório" + ex.toString(),JOptionPane.ERROR_MESSAGE);   
		    }  
			
			
			
			
			
			
			//CRIA PDF
	        OutputStream os;
//	        if(data.equals("13O. SAL-2015")){
//	        	data="MAIUSCULO";
//	        }
//	        else if(data.equals("13o. SAL-2015")){
//	        	data="MINUSCULO";
//	        }
//	        else{
//	        	data="NENHUMDOSDOIS";
//	        }
			try {
				os = new FileOutputStream(Caminhos.dirCriacaoPDF+sufixo+"\\"+f.getCpf()+"_"+data+".pdf");
	        PdfWriter.getInstance(doc, os);
	        doc.open();
	        Image logo = Image.getInstance(Caminhos.dirLogo);
	        logo.setAlignment(Image.ALIGN_CENTER);
	        doc.add(logo);
	         

	        Font f1 = new Font(FontFamily.COURIER, 20, Font.BOLD);
	        Font f2 = new Font(FontFamily.COURIER, 8, Font.BOLD);
	        Font f3 = new Font(FontFamily.COURIER, 9, Font.NORMAL);
	 
	        Paragraph p = new Paragraph("Demonstrativo de Pagamento de Salário", f1);
	        Paragraph p2 = new Paragraph("\n");
	 

	        p.setAlignment(Paragraph.ALIGN_CENTER);
	        p.setSpacingAfter(50);
	        doc.add(p);
	 
	        PdfPTable table = new PdfPTable(40);
	        
//			################### TABELA 1 ###################
//	      ##### LINHA 1 ##### 
	        Paragraph torgao = new Paragraph("Nome do Órgão", f2);
	        PdfPCell torgaoc = new PdfPCell(torgao);
	        torgaoc.setColspan(24);
	        torgaoc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        torgaoc.disableBorderSide(2);
	        table.addCell(torgaoc);
	        Paragraph tcnpj = new Paragraph("CNPJ\\CEI", f2);
	        PdfPCell tcnpjc = new PdfPCell(tcnpj);
	        tcnpjc.setColspan(16);
	        tcnpjc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tcnpjc.disableBorderSide(2);
	        table.addCell(tcnpjc);
	        
	        Paragraph orgao = new Paragraph("CENTRAIS DE ABASTECIMENTO DE GOIÁS", f3);
	        PdfPCell orgaoc = new PdfPCell(orgao);
	        orgaoc.setColspan(24);
	        orgaoc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        orgaoc.disableBorderSide(1);
	        table.addCell(orgaoc);
	        Paragraph cnpj = new Paragraph("01.098.797/0001-74", f3);
	        PdfPCell cnpjc = new PdfPCell(cnpj);
	        cnpjc.setColspan(16);
	        cnpjc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        cnpjc.disableBorderSide(1);
	        table.addCell(cnpjc);
	        
//	      ##### LINHA 2 #####
	        Paragraph tnome = new Paragraph("Nome do Servidor", f2);
	        PdfPCell tnomec = new PdfPCell(tnome);
	        tnomec.setColspan(22);
	        tnomec.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tnomec.disableBorderSide(2);
	        table.addCell(tnomec);
	        Paragraph tcpf = new Paragraph("CPF", f2);
	        PdfPCell tcpfc = new PdfPCell(tcpf);
	        tcpfc.setColspan(7);
	        tcpfc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tcpfc.disableBorderSide(2);
	        table.addCell(tcpfc);
	        Paragraph tmatricula = new Paragraph("Matrícula", f2);
	        PdfPCell tmatriculac = new PdfPCell(tmatricula);
	        tmatriculac.setColspan(4);
	        tmatriculac.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tmatriculac.disableBorderSide(2);
	        table.addCell(tmatriculac);
	        Paragraph treferencia = new Paragraph("Referência", f2);
	        PdfPCell treferenciac = new PdfPCell(treferencia);
	        treferenciac.setColspan(7);
	        treferenciac.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        treferenciac.disableBorderSide(2);
	        table.addCell(treferenciac);
	        
	        Paragraph nome = new Paragraph(f.getNome(), f3);
	        PdfPCell nomec = new PdfPCell(nome);
	        nomec.setColspan(22);
	        nomec.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        nomec.disableBorderSide(1);
	        table.addCell(nomec);
	        Paragraph cpf = new Paragraph(f.getCpf(), f3);
	        PdfPCell cpfc = new PdfPCell(cpf);
	        cpfc.setColspan(7);
	        cpfc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        cpfc.disableBorderSide(1);
	        table.addCell(cpfc);
	        Paragraph matricula = new Paragraph(String.valueOf(f.getMatricula()), f3);
	        PdfPCell cmatricula = new PdfPCell(matricula);
	        cmatricula.setColspan(4);
	        cmatricula.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        cmatricula.disableBorderSide(1);
	        table.addCell(cmatricula);
	        Paragraph referencia = new Paragraph(f.getReferencia(), f3);
	        PdfPCell creferencia = new PdfPCell(referencia);
	        creferencia.setColspan(7);
	        creferencia.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        creferencia.disableBorderSide(1);
	        table.addCell(creferencia);
	        
	        
//	      ##### LINHA 3 #####
	        Paragraph tcbo = new Paragraph("CBO", f2);
	        PdfPCell tcboc = new PdfPCell(tcbo);
	        tcboc.setColspan(5);
	        tcboc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tcboc.disableBorderSide(2);
	        table.addCell(tcboc);
	        Paragraph tcargo = new Paragraph("Cargo", f2);
	        PdfPCell tcargoc = new PdfPCell(tcargo);
	        tcargoc.setColspan(23);
	        tcargoc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tcargoc.disableBorderSide(2);
	        table.addCell(tcargoc);
	        Paragraph tlotacao = new Paragraph("Lotação", f2);
	        PdfPCell tlotacaoc = new PdfPCell(tlotacao);
	        tlotacaoc.setColspan(4);
	        tlotacaoc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tlotacaoc.disableBorderSide(2);
	        table.addCell(tlotacaoc);
	        Paragraph tsituacao = new Paragraph("Situação", f2);
	        PdfPCell tsituacaoc = new PdfPCell(tsituacao);
	        tsituacaoc.setColspan(8);
	        tsituacaoc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tsituacaoc.disableBorderSide(2);
	        table.addCell(tsituacaoc);
	        
	        
	        
	        
	        Paragraph cbo = new Paragraph(String.valueOf(f.getCbo()), f3);
	        PdfPCell cboc = new PdfPCell(cbo);
	        cboc.setColspan(5);
	        cboc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        cboc.disableBorderSide(1);
	        table.addCell(cboc);
	        Paragraph cargo = new Paragraph(f.getCargo(), f3);
	        PdfPCell cargoc = new PdfPCell(cargo);
	        cargoc.setColspan(23);
	        cargoc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        cargoc.disableBorderSide(1);
	        table.addCell(cargoc);
	        Paragraph lotacao = new Paragraph(String.valueOf(f.getLotacao()), f3);
	        PdfPCell lotacaoc = new PdfPCell(lotacao);
	        lotacaoc.setColspan(4);
	        lotacaoc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        lotacaoc.disableBorderSide(1);
	        table.addCell(lotacaoc);
	        Paragraph situacao = new Paragraph(f.getSituacao(), f3);
	        PdfPCell situacaoc = new PdfPCell(situacao);
	        situacaoc.setColspan(8);
	        situacaoc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        situacaoc.disableBorderSide(1);
	        table.addCell(situacaoc);
	        
//	      ##### LINHA 4 #####
	        Paragraph tcodbanco = new Paragraph("Código do Banco", f2);
	        PdfPCell tcodbancoc = new PdfPCell(tcodbanco);
	        tcodbancoc.setColspan(13);
	        tcodbancoc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tcodbancoc.disableBorderSide(2);
	        table.addCell(tcodbancoc);
	        Paragraph tagencia = new Paragraph("Agência", f2);
	        PdfPCell tagenciac = new PdfPCell(tagencia);
	        tagenciac.setColspan(13);
	        tagenciac.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tagenciac.disableBorderSide(2);
	        table.addCell(tagenciac);
	        Paragraph tconta = new Paragraph("Conta Nº", f2);
	        PdfPCell tcontac = new PdfPCell(tconta);
	        tcontac.setColspan(14);
	        tcontac.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tcontac.disableBorderSide(2);
	        table.addCell(tcontac);
	        
	        Paragraph codbanco = new Paragraph(String.valueOf(f.getCodBanco()), f3);
	        PdfPCell codbancoc = new PdfPCell(codbanco);
	        codbancoc.setColspan(13);
	        codbancoc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        codbancoc.disableBorderSide(1);
	        table.addCell(codbancoc);
	        Paragraph agencia = new Paragraph(String.valueOf(f.getAgencia()), f3);
	        PdfPCell agenciac = new PdfPCell(agencia);
	        agenciac.setColspan(13);
	        agenciac.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        agenciac.disableBorderSide(1);
	        table.addCell(agenciac);
	        Paragraph conta = new Paragraph(String.valueOf(f.getConta()), f3);
	        PdfPCell contac = new PdfPCell(conta);
	        contac.setColspan(14);
	        contac.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        contac.disableBorderSide(1);
	        table.addCell(contac);
	        
	        doc.add(table);
	        
	        doc.add(p2);
	        
	        
//			################### TABELA 2 ###################
	        PdfPTable table2 = new PdfPTable(40);	        
	        
//		      ##### CABEÇALHO ##### 
	        Paragraph tcodigo = new Paragraph("Código", f2);
	        PdfPCell tcodigoc = new PdfPCell(tcodigo);
	        tcodigoc.setColspan(4);
	        tcodigoc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tcodigoc.disableBorderSide(2);
	        table2.addCell(tcodigoc);
	        Paragraph tdescricao = new Paragraph("Descrição", f2);
	        PdfPCell tdescricaoc = new PdfPCell(tdescricao);
	        tdescricaoc.setColspan(18);
	        tdescricaoc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tdescricaoc.disableBorderSide(2);
	        table2.addCell(tdescricaoc);        
	        Paragraph taliquota = new Paragraph("Alíquota", f2);
	        PdfPCell taliquotac = new PdfPCell(taliquota);
	        taliquotac.setColspan(6);
	        taliquotac.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        taliquotac.disableBorderSide(2);
	        table2.addCell(taliquotac);
	        Paragraph tvencimentos = new Paragraph("Vencimentos", f2);
	        PdfPCell tvencimentosc = new PdfPCell(tvencimentos);
	        tvencimentosc.setColspan(6);
	        tvencimentosc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tvencimentosc.disableBorderSide(2);
	        table2.addCell(tvencimentosc);
	        Paragraph tdescontos = new Paragraph("Descontos", f2);
	        PdfPCell tdescontosc = new PdfPCell(tdescontos);
	        tdescontosc.setColspan(6);
	        tdescontosc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tdescontosc.disableBorderSide(2);
	        table2.addCell(tdescontosc);
	        
	        
	        for (int j = 0; j < f.getProventos().size(); j++) {
	        	
	        	Paragraph codigo = new Paragraph(String.valueOf(f.getProventos().get(j).getCodigo()), f3);
	        	Paragraph descricao = new Paragraph(f.getProventos().get(j).getDescricao(), f3);
	        	Paragraph aliquota = new Paragraph(String.valueOf(f.getProventos().get(j).getAliquota()), f3);
	        	Paragraph vencimentos = new Paragraph(String.valueOf(f.getProventos().get(j).getVencimentos()), f3);
	        	Paragraph descontos = new Paragraph(String.valueOf(f.getProventos().get(j).getDescontos()), f3);
	        
	        PdfPCell codigoc = new PdfPCell(codigo);
	        codigoc.setColspan(4);
	        codigoc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
//	        codigoc.disableBorderSide(2);
	        PdfPCell descricaoc = new PdfPCell(descricao);
	        descricaoc.setColspan(18);
	        descricaoc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
//	        descricaoc.disableBorderSide(2);
	        PdfPCell aliquotac = new PdfPCell(aliquota);
	        aliquotac.setColspan(6);
	        aliquotac.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
//	        aliquotac.disableBorderSide(2);
	        PdfPCell vencimentosc = new PdfPCell(vencimentos);
	        vencimentosc.setColspan(6);
	        vencimentosc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
//	        vencimentosc.disableBorderSide(2);
	        PdfPCell descontosc = new PdfPCell(descontos);
	        descontosc.setColspan(6);
	        descontosc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
//	        descontosc.disableBorderSide(2);
	        
	        table2.addCell(codigoc);
	        table2.addCell(descricaoc);
	        table2.addCell(aliquotac);
	        table2.addCell(vencimentosc);
	        table2.addCell(descontosc);
	        }
	        
	        
	        doc.add(table2);
	        
	        doc.add(p2);
//			################### TABELA 3 ###################
	        PdfPTable table3 = new PdfPTable(20);
	        
//	      ##### LINHA 1 ##### 
	        Paragraph tsalcont = new Paragraph("Sal Cont INSS", f2);
	        PdfPCell tsalcontc = new PdfPCell(tsalcont);
	        tsalcontc.setColspan(4);
	        tsalcontc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tsalcontc.disableBorderSide(2);
	        table3.addCell(tsalcontc);
	        Paragraph tbasefgts = new Paragraph("Base Calc FGTS", f2);
	        PdfPCell tbasefgtsc = new PdfPCell(tbasefgts);
	        tbasefgtsc.setColspan(4);
	        tbasefgtsc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tbasefgtsc.disableBorderSide(2);
	        table3.addCell(tbasefgtsc);        
	        Paragraph tvalor = new Paragraph("Valor FGTS", f2);
	        PdfPCell tvalorc = new PdfPCell(tvalor);
	        tvalorc.setColspan(4);
	        tvalorc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tvalorc.disableBorderSide(2);
	        table3.addCell(tvalorc);
	        Paragraph tbaseirrf = new Paragraph("Base IRRF", f2);
	        PdfPCell tbaseirrfc = new PdfPCell(tbaseirrf);
	        tbaseirrfc.setColspan(4);
	        tbaseirrfc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tbaseirrfc.disableBorderSide(2);
	        table3.addCell(tbaseirrfc);
	        Paragraph tdepirrf = new Paragraph("Dep IRRF", f2);
	        PdfPCell tdepirrfc = new PdfPCell(tdepirrf);
	        tdepirrfc.setColspan(4);
	        tdepirrfc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tdepirrfc.disableBorderSide(2);
	        table3.addCell(tdepirrfc);
	        
	        Paragraph salcont = new Paragraph(String.valueOf(f.getSalContInss()), f3);
	        PdfPCell salcontc = new PdfPCell(salcont);
	        salcontc.setColspan(4);
	        salcontc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        salcontc.disableBorderSide(1);
	        table3.addCell(salcontc);
	        Paragraph basefgts = new Paragraph(String.valueOf(f.getBaseCalcFgts()), f3);
	        PdfPCell basefgtsc = new PdfPCell(basefgts);
	        basefgtsc.setColspan(4);
	        basefgtsc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        basefgtsc.disableBorderSide(1);
	        table3.addCell(basefgtsc);
	        Paragraph valor = new Paragraph(String.valueOf(f.getValorFgts()), f3);
	        PdfPCell valorc = new PdfPCell(valor);
	        valorc.setColspan(4);
	        valorc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        valorc.disableBorderSide(1);
	        table3.addCell(valorc);
	        Paragraph baseirrf = new Paragraph(String.valueOf(f.getBaseCalcIrrf()), f3);
	        PdfPCell baseirrfc = new PdfPCell(baseirrf);
	        baseirrfc.setColspan(4);
	        baseirrfc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        baseirrfc.disableBorderSide(1);
	        table3.addCell(baseirrfc);
	        Paragraph depirrf = new Paragraph(String.valueOf(f.getDepIrrf()), f3);
	        PdfPCell depirrfc = new PdfPCell(depirrf);
	        depirrfc.setColspan(4);
	        depirrfc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        depirrfc.disableBorderSide(1);
	        table3.addCell(depirrfc);
	        
//	      ##### LINHA 2 ##### 
	        Paragraph tproventos = new Paragraph("Total de Proventos", f2);
	        PdfPCell tproventosc = new PdfPCell(tproventos);
	        tproventosc.setColspan(6);
	        tproventosc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tproventosc.disableBorderSide(2);
	        table3.addCell(tproventosc);
	        Paragraph tdescontos2 = new Paragraph("Total de Descontos", f2);
	        PdfPCell tdescontosc2 = new PdfPCell(tdescontos2);
	        tdescontosc2.setColspan(6);
	        tdescontosc2.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tdescontosc2.disableBorderSide(2);
	        table3.addCell(tdescontosc2);        
	        Paragraph tliquido = new Paragraph("Total Líquido", f2);
	        PdfPCell tliquidoc = new PdfPCell(tliquido);
	        tliquidoc.setColspan(8);
	        tliquidoc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tliquidoc.disableBorderSide(2);
	        table3.addCell(tliquidoc); 
	        
	        Paragraph proventos = new Paragraph(String.valueOf(f.getTotalProventos()), f3);
	        PdfPCell proventosc = new PdfPCell(proventos);
	        proventosc.setColspan(6);
	        proventosc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        proventosc.disableBorderSide(1);
	        table3.addCell(proventosc);
	        Paragraph descontos2 = new Paragraph(String.valueOf(f.getTotalDescontos()), f3);
	        PdfPCell descontosc2 = new PdfPCell(descontos2);
	        descontosc2.setColspan(6);
	        descontosc2.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        descontosc2.disableBorderSide(1);
	        table3.addCell(descontosc2);
	        Paragraph liquido = new Paragraph(String.valueOf(f.getTotalLiquido()), f3);
	        PdfPCell liquidoc = new PdfPCell(liquido);
	        liquidoc.setColspan(8);
	        liquidoc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        liquidoc.disableBorderSide(1);
	        table3.addCell(liquidoc);
	        
	        doc.add(table3);
	        doc.add(p2);
//			################### TABELA 4 ###################
	        PdfPTable table4 = new PdfPTable(20);
	        
//	      ##### CAMPO MENSAGEM ##### 
	        Paragraph tmensagem = new Paragraph("Mensagem", f2);
	        PdfPCell tmensagemc = new PdfPCell(tmensagem);
	        tmensagemc.setColspan(20);
	        tmensagemc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        tmensagemc.disableBorderSide(2);
	        table4.addCell(tmensagemc);
	        
	        Paragraph mensagem = new Paragraph(InputTextAreaMB.msg, f3);
	        PdfPCell mensagemc = new PdfPCell(mensagem);
	        mensagemc.setColspan(20);
	        mensagemc.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
	        mensagemc.disableBorderSide(1);
	        table4.addCell(mensagemc);
	        	        
	        doc.add(table4);

	        
	        doc.close();
	        
			} catch (DocumentException | IOException e) {
				e.printStackTrace();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro: %s", e.getMessage());  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
	        System.out.println("Pdf criado com sucesso!");
	    }
}
