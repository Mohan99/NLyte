package com.gcs.nlyte.web.action;

import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.gcs.nlyte.web.bean.NltyeCustDataProcessBean;
import com.gcs.nlyte.web.bean.NlyteCustMastDataBean;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataTransStg;

public class NlyteCustTransDataViewAction extends NLyteAction {
	private static final long serialVersionUID = 1L;
	String cmsId;
	String exportfile;
	private List<NlyteCustMastDataBean> nlyteCustMastDataBean = new ArrayList<NlyteCustMastDataBean>();
	private List<NltyeCustDataProcessBean> nltyeCustDataProcessBean = new ArrayList<NltyeCustDataProcessBean>();

	private List<NltyeCustDataProcessBean> listBean;
	private List<NltyeCustDataProcessBean> selectBean;

	public List<NltyeCustDataProcessBean> getListBean() {
		return listBean;
	}

	public void setListBean(List<NltyeCustDataProcessBean> listBean) {
		this.listBean = listBean;
	}

	public List<NltyeCustDataProcessBean> getSelectBean() {
		return selectBean;
	}

	public void setSelectBean(List<NltyeCustDataProcessBean> selectBean) {
		this.selectBean = selectBean;
	}

	public List<NltyeCustDataProcessBean> getNltyeCustDataProcessBean() {
		return nltyeCustDataProcessBean;
	}

	public void setNltyeCustDataProcessBean(List<NltyeCustDataProcessBean> nltyeCustDataProcessBean) {
		this.nltyeCustDataProcessBean = nltyeCustDataProcessBean;
	}

	public List<NlyteCustMastDataBean> getNlyteCustMastDataBean() {
		return nlyteCustMastDataBean;
	}

	public void setNlyteCustMastDataBean(List<NlyteCustMastDataBean> nlyteCustMastDataBean) {
		this.nlyteCustMastDataBean = nlyteCustMastDataBean;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	private HttpServletResponse response;
	private List<NlyteCustomerDataTransStg> nlyteCustDataTransStg = new ArrayList<NlyteCustomerDataTransStg>();

	public List<NlyteCustomerDataTransStg> getNlyteCustDataTransStg() {
		return nlyteCustDataTransStg;
	}

	public void setNlyteCustDataTransStg(List<NlyteCustomerDataTransStg> nlyteCustDataTransStg) {
		this.nlyteCustDataTransStg = nlyteCustDataTransStg;
	}

	public String getCmsId() {
		return cmsId;
	}

	public void setCmsId(String cmsId) {
		this.cmsId = cmsId;
	}

	private List<NlyteMasterDataTransStg> nlyteMasterDataTransStg = new ArrayList<NlyteMasterDataTransStg>();

	@Override
	public String execute() {
		try {
			List<NlyteCustomerDataTransStg> nlyteCustDtTransUpdated = this.nlyteService
					.getCustomerDataTransStgList(getCmsId());

			setNlyteCustDataTransStg(nlyteCustDtTransUpdated);
			return "success";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}

	}

	public List<NlyteMasterDataTransStg> getNlyteMasterDataTransStg() {
		return nlyteMasterDataTransStg;
	}

	public void setNlyteMasterDataTransStg(List<NlyteMasterDataTransStg> nlyteMasterDataTransStg) {
		this.nlyteMasterDataTransStg = nlyteMasterDataTransStg;
	}

	public String viewCustProcessed() {
		try {
			List<NlyteCustMastDataBean> nlyteCustDtTransUpdated = this.nlyteService
					.getCustomerDataTransStgProcessedList(getCmsId());
			setNlyteCustMastDataBean(nlyteCustDtTransUpdated);
			return "success";

		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}

	}

	public String exportProcessedData() {
		try {
			List<NlyteCustMastDataBean> nlyteCustDtTransUpdated = this.nlyteService
					.getCustomerDataTransStgProcessedList(getCmsId());
			setNlyteCustMastDataBean(nlyteCustDtTransUpdated);

			NlyteCustomerDataStg nlyteCustDataStg = this.nlyteService.getCustomerDtStgById(getCmsId());
			exportfile = nlyteCustDataStg.getDescriptionTx();
			exportfile = exportfile.substring(0, exportfile.lastIndexOf("."));

			if (nlyteCustDtTransUpdated != null && nlyteCustDtTransUpdated.size() > 0) {
				doExportMatched(nlyteCustDtTransUpdated);

				return "success";
			} else {
				return "error";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}

	}

	public String viewCustUnProcessed() {
		try {
			List<NlyteCustomerDataTransStg> nlyteCustDtTransUpdated = this.nlyteService
					.getCustomerDataTransStgUnProcessedList(getCmsId());
			setNlyteCustDataTransStg(nlyteCustDtTransUpdated);
			return "success";

		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}

	}

	public String exportUnProcessedData() {
		try {
			List<NlyteCustomerDataTransStg> nlyteCustDtTransUpdated = this.nlyteService
					.getCustomerDataTransStgUnProcessedList(getCmsId());
			setNlyteCustDataTransStg(nlyteCustDtTransUpdated);

			NlyteCustomerDataStg nlyteCustDataStg = this.nlyteService.getCustomerDtStgById(getCmsId());
			exportfile = nlyteCustDataStg.getDescriptionTx();
			exportfile = exportfile.substring(0, exportfile.lastIndexOf("."));

			if (nlyteCustDtTransUpdated != null && nlyteCustDtTransUpdated.size() > 0) {
				doExport(nlyteCustDtTransUpdated);

				return "success";
			} else {
				return "error";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}

	}

	public String viewCustMultiMatched() {
		/*
		 * try { List<NltyeCustDataProcessBean> nlyteCustDtTransUpdated =
		 * this.nlyteService .getCustomerDataTransStgMultiMatchedList(getCmsId());
		 * setNltyeCustDataProcessBean(nlyteCustDtTransUpdated); return "success";
		 * 
		 * } catch (Exception ex) { ex.printStackTrace(); return "error"; }
		 */

		boolean flag = Boolean.FALSE;
		try {
			List<NltyeCustDataProcessBean> nlyteCustDtTransUpdated = this.nlyteService
					.getCustomerDataProcessedList(getCmsId());
			List<NltyeCustDataProcessBean> nlyteCustDtTransUpdatedAll = this.nlyteService
					.findCustProcessedAllList(getCmsId());

			setListBean(nlyteCustDtTransUpdated);
			setSelectBean(nlyteCustDtTransUpdatedAll);

			return "success";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}

	}

	public String exportMultiMatchedData() {
		try {

			List<NltyeCustDataProcessBean> nlyteCustDtTransUpdated = this.nlyteService
					.getCustomerDataProcessedList(getCmsId());
			List<NltyeCustDataProcessBean> nlyteCustDtTransUpdatedAll = this.nlyteService
					.findCustProcessedAllList(getCmsId());
			setListBean(nlyteCustDtTransUpdated);
			setSelectBean(nlyteCustDtTransUpdatedAll);

			/*
			 * List<NltyeCustDataProcessBean> nlyteCustDtTransUpdated = this.nlyteService
			 * .getCustomerDataTransStgMultiMatchedList(getCmsId());
			 * setNltyeCustDataProcessBean(nlyteCustDtTransUpdated);
			 */

			NlyteCustomerDataStg nlyteCustDataStg = this.nlyteService.getCustomerDtStgById(getCmsId());
			exportfile = nlyteCustDataStg.getDescriptionTx();
			exportfile = exportfile.substring(0, exportfile.lastIndexOf("."));

			if (nlyteCustDtTransUpdated != null && nlyteCustDtTransUpdated.size() > 0) {
				doExportMultiMatched(nlyteCustDtTransUpdated, nlyteCustDtTransUpdatedAll);

				return "success";
			} else {
				return "error";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}

	}

	private void downloadFile(final String fileName, HttpServletResponse response) {
		final File f = new File(fileName);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "inline; filename=" + fileName);
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "no-store");
		response.addHeader("Cache-Control", "max-age=0");
		FileInputStream fin = null;
		ServletOutputStream os = null;
		try {
			fin = new FileInputStream(f);
			final int size = 1024;
			response.setContentLength(fin.available());
			final byte[] buffer = new byte[size];
			os = response.getOutputStream();
			int length = 0;
			while ((length = fin.read(buffer)) != -1) {
				os.write(buffer, 0, length);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fin != null)
					fin.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (os != null)
					os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void doExport(List<NlyteCustomerDataTransStg> dataList) throws Exception {
		if (dataList != null && !dataList.isEmpty()) {
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet();
			HSSFRow headingRow = sheet.createRow(0);
			headingRow.createCell((short) 0).setCellValue("S.NO");
			headingRow.createCell((short) 1).setCellValue("Model");
			headingRow.createCell((short) 2).setCellValue("Updated Model");
			headingRow.createCell((short) 3).setCellValue("Manufacturer");
			headingRow.createCell((short) 4).setCellValue("Material Type");
			headingRow.createCell((short) 5).setCellValue("Material Sub Type");
			headingRow.createCell((short) 6).setCellValue("Power Consuption");
			headingRow.createCell((short) 7).setCellValue("Width");
			headingRow.createCell((short) 8).setCellValue("Depth");
			headingRow.createCell((short) 9).setCellValue("Height");
			headingRow.createCell((short) 10).setCellValue("Weight");
			headingRow.createCell((short) 11).setCellValue("Copper Ports");
			headingRow.createCell((short) 12).setCellValue("Fiber Ports");
			/*
			 * headingRow.createCell((short)12).setCellValue("Created Date");
			 * headingRow.createCell((short)13).setCellValue("Updated Date");
			 */
			/* headingRow.createCell((short)12).setCellValue("Status"); */
			short rowNo = 1;
			for (NlyteCustomerDataTransStg details : dataList) {
				HSSFRow row = sheet.createRow(rowNo);
				row.createCell((short) 0).setCellValue(rowNo);
				row.createCell((short) 1).setCellValue(details.getModelTx());
				row.createCell((short) 2).setCellValue(details.getNmsValue());
				row.createCell((short) 3).setCellValue(details.getManufacturerTx());
				row.createCell((short) 4).setCellValue(details.getMaterialTypTx());
				row.createCell((short) 5).setCellValue(details.getMaterialSubTypTx());
				row.createCell((short) 6).setCellValue(details.getPowerConsmptNm());
				row.createCell((short) 7).setCellValue(details.getWidthNm());
				row.createCell((short) 8).setCellValue(details.getDepthNm());
				row.createCell((short) 9).setCellValue(details.getHeightNm());
				row.createCell((short) 10).setCellValue(details.getWeightNm());
				row.createCell((short) 11).setCellValue(details.getCopperPortsTx());
				row.createCell((short) 12).setCellValue(details.getFiberPortsTx());
				/*
				 * SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); String
				 * strDate= formatter.format(details.getCreatedDt());
				 * row.createCell((short)12).setCellValue(strDate); String strDate1=
				 * formatter.format(details.getUpdatedDt());
				 * row.createCell((short)13).setCellValue(strDate1);
				 */
				/*
				 * row.createCell((short)12).setCellValue(details.isValidYn());
				 */
				rowNo++;
			}

			String file = "UnmatchedData_" + exportfile + ".xls";
			try {
				FileOutputStream fos = new FileOutputStream(file);
				workBook.write(fos);
				fos.flush();
				fos.close();
				HttpServletResponse response = ServletActionContext.getResponse();
				downloadFile(file, response);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void doExportMatched(List<NlyteCustMastDataBean> dataList) throws Exception {
		HSSFWorkbook workBook = new HSSFWorkbook();
		if (dataList != null && !dataList.isEmpty()) {
			
			NlyteCustMastDataBean nlyteCustMastDataBean = new NlyteCustMastDataBean();

			HSSFFont font= workBook.createFont();				       
	        font.setColor(IndexedColors.BLUE.getIndex());				        			                                    
	         // Create cell style 
	        CellStyle style=workBook.createCellStyle();    			        
	        // Setting font to style
	        style.setFont(font);
	        
			
			HSSFSheet sheet = workBook.createSheet();
			HSSFRow headingRow = sheet.createRow(0);
			
			
			Cell ch0=headingRow.createCell((short) 0);
			ch0.setCellStyle(style);
			ch0.setCellValue("S.NO");
			
			headingRow.createCell((short) 1).setCellValue("Model");
			
			Cell ch2=headingRow.createCell((short) 2);
			ch2.setCellStyle(style);
			ch2.setCellValue("NLyte Material name");	
			
			Cell ch3=headingRow.createCell((short) 3);
			ch3.setCellStyle(style);
			ch3.setCellValue("Manufacturer");	
			
			Cell ch4=headingRow.createCell((short) 4);
			ch4.setCellStyle(style);
			ch4.setCellValue("Material Type");	
			
			Cell ch5=headingRow.createCell((short) 5);
			ch5.setCellStyle(style);
			ch5.setCellValue("Material Sub Type");	
			
			Cell ch6=headingRow.createCell((short) 6);
			ch6.setCellStyle(style);
			ch6.setCellValue("Power Consuption");	
			
			Cell ch7=headingRow.createCell((short) 7);
			ch7.setCellStyle(style);
			ch7.setCellValue("Width");	
			
			Cell ch8=headingRow.createCell((short) 8);
			ch8.setCellStyle(style);
			ch8.setCellValue("Depth");	
			
			Cell ch9=headingRow.createCell((short) 9);
			ch9.setCellStyle(style);
			ch9.setCellValue("Height");
			
			Cell ch10=headingRow.createCell((short) 10);
			ch10.setCellStyle(style);
			ch10.setCellValue("Weight");
			
			Cell ch11=headingRow.createCell((short) 11);
			ch11.setCellStyle(style);
			ch11.setCellValue("Copper Ports");
			
			Cell ch12=headingRow.createCell((short) 12);
			ch12.setCellStyle(style);
			ch12.setCellValue("Fiber Ports");
		

			headingRow.createCell((short) 13).setCellValue("Manufaturer");
			headingRow.createCell((short) 14).setCellValue("Material Type");
			headingRow.createCell((short) 15).setCellValue("material Sub Type");
			headingRow.createCell((short) 16).setCellValue("Width");
			headingRow.createCell((short) 17).setCellValue("Depth");
			headingRow.createCell((short) 18).setCellValue("Height");
			headingRow.createCell((short) 19).setCellValue("Weight");
			headingRow.createCell((short) 20).setCellValue("Copper Ports");
			headingRow.createCell((short) 21).setCellValue("Fiber Ports");
			headingRow.createCell((short) 22).setCellValue("Undefined Ports");
			headingRow.createCell((short) 23).setCellValue("Power Consumption");
			headingRow.createCell((short) 24).setCellValue("Match Percentage");

			short rowNo = 1;
			for (NlyteCustMastDataBean details : dataList) {
				
								       
		        /*font.setColor(IndexedColors.BLUE.getIndex());	*/			        			                                    
		         // Create cell style 
		        
		        /*style.setFont(font);*/
				NlyteCustomerDataTransStg nlyteCustomerDataTransStg = details.getnCustDataTransStg();
				NlyteMasterDataTransStg nlyteMasterDataTransStg = details.getnMasterDataTrans();
				

				HSSFRow row = sheet.createRow(rowNo);
				Cell c0=row.createCell((short) 0);
				c0.setCellStyle(style);
				c0.setCellValue(rowNo);
				
				Cell c1=row.createCell((short) 1);
				row.createCell((short) 1).setCellValue(nlyteCustomerDataTransStg.getModelTx());
				
				Cell c2=row.createCell((short) 2);
				c2.setCellStyle(style);
				c2.setCellValue(nlyteMasterDataTransStg.getMaterialNmTx());
				
				Cell c3=row.createCell((short) 3);
				c3.setCellStyle(style);
				c3.setCellValue(nlyteMasterDataTransStg.getManufacturerTx());
				
				Cell c4=row.createCell((short) 4);
				c4.setCellStyle(style);
				c4.setCellValue(nlyteMasterDataTransStg.getMaterialTypTx());
				
				Cell c5=row.createCell((short) 5);
				c5.setCellStyle(style);
				c5.setCellValue(nlyteMasterDataTransStg.getMaterialSubTypTx());
				
				Cell c6=row.createCell((short) 6);
				c6.setCellStyle(style);
				c6.setCellValue(nlyteMasterDataTransStg.getPowerConsmptNm());
				
				Cell c7=row.createCell((short) 7);
				c7.setCellStyle(style);
				c7.setCellValue(nlyteMasterDataTransStg.getWidthNm());
				
				Cell c8=row.createCell((short) 8);
				c8.setCellStyle(style);
				c8.setCellValue(nlyteMasterDataTransStg.getDepthNm());
				
				Cell c9=row.createCell((short) 9);
				c9.setCellStyle(style);
				c9.setCellValue(nlyteMasterDataTransStg.getHeightNm());
				
				Cell c10=row.createCell((short) 10);
				c10.setCellStyle(style);
				c10.setCellValue(nlyteMasterDataTransStg.getWeightNm());
				
				
				
				Cell c11=row.createCell((short) 11);
				c11.setCellStyle(style);
				c11.setCellValue(nlyteMasterDataTransStg.getCopperPortsTx());
				
				Cell c12=row.createCell((short) 12);
				c12.setCellStyle(style);
				c12.setCellValue(nlyteMasterDataTransStg.getFiberPortsTx());
				
				row.createCell((short) 13).setCellValue(nlyteCustomerDataTransStg.getManufacturerTx());
				row.createCell((short) 14).setCellValue(nlyteCustomerDataTransStg.getMaterialTypTx());
				row.createCell((short) 15).setCellValue(nlyteCustomerDataTransStg.getMaterialSubTypTx());
				row.createCell((short) 16).setCellValue(nlyteCustomerDataTransStg.getWidthNm());
				row.createCell((short) 17).setCellValue(nlyteCustomerDataTransStg.getDepthNm());
				row.createCell((short) 18).setCellValue(nlyteCustomerDataTransStg.getHeightNm());
				row.createCell((short) 19).setCellValue(nlyteCustomerDataTransStg.getWeightNm());
				row.createCell((short) 20).setCellValue(nlyteCustomerDataTransStg.getCopperPortsTx());
				row.createCell((short) 21).setCellValue(nlyteCustomerDataTransStg.getFiberPortsTx());
				row.createCell((short) 22).setCellValue(nlyteCustomerDataTransStg.getUndefPortsTx());
				row.createCell((short) 23).setCellValue(nlyteCustomerDataTransStg.getPowerConsmptNm());
				row.createCell((short) 24).setCellValue(nlyteCustomerDataTransStg.getMatchPercentage());

				rowNo++;

			}
			String file = "MatchedData_" + exportfile + ".xls";
			try {
				FileOutputStream fos = new FileOutputStream(file);
				workBook.write(fos);
				fos.flush();
				fos.close();
				HttpServletResponse response = ServletActionContext.getResponse();
				downloadFile(file, response);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void doExportMultiMatched(List<NltyeCustDataProcessBean> dataList, List<NltyeCustDataProcessBean> masterList)
			throws Exception {
		HSSFWorkbook workBook = new HSSFWorkbook();
		if (dataList != null && !dataList.isEmpty()) {

			 workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet();
			HSSFRow headingRow = sheet.createRow(0);
			headingRow.createCell((short) 0).setCellValue("S.NO");
			headingRow.createCell((short) 1).setCellValue("Model");
			headingRow.createCell((short) 2).setCellValue("Nlyte Material name");
			headingRow.createCell((short) 3).setCellValue("Manufacturer");
			headingRow.createCell((short) 4).setCellValue("Material Type");
			headingRow.createCell((short) 5).setCellValue("Material Sub Type");
			headingRow.createCell((short) 6).setCellValue("Width");
			headingRow.createCell((short) 7).setCellValue("Depth");
			headingRow.createCell((short) 8).setCellValue("Height");
			headingRow.createCell((short) 9).setCellValue("Weight");
			headingRow.createCell((short) 10).setCellValue("Copper Ports");
			headingRow.createCell((short) 11).setCellValue("Fiber Ports");
			headingRow.createCell((short) 12).setCellValue("Undefined Ports");
			headingRow.createCell((short) 13).setCellValue("Power Consumption");

			HSSFRow row =null;
			short rowNo = 1;
			int sNo=1;
			for (NltyeCustDataProcessBean details : dataList) {
				NlyteCustomerDataTransStg nlyteCustomerDataTransStg = details.getnCustDataTransStg();

				 row = sheet.createRow(rowNo);
				row.createCell((short) 0).setCellValue(sNo);
				row.createCell((short) 1).setCellValue(nlyteCustomerDataTransStg.getModelTx());
				row.createCell((short) 2).setCellValue("");
				row.createCell((short) 3).setCellValue(nlyteCustomerDataTransStg.getManufacturerTx());
				row.createCell((short) 4).setCellValue(nlyteCustomerDataTransStg.getMaterialTypTx());
				row.createCell((short) 5).setCellValue(nlyteCustomerDataTransStg.getMaterialSubTypTx());
				row.createCell((short) 6).setCellValue(nlyteCustomerDataTransStg.getWidthNm());
				row.createCell((short) 7).setCellValue(nlyteCustomerDataTransStg.getDepthNm());
				row.createCell((short) 8).setCellValue(nlyteCustomerDataTransStg.getHeightNm());
				row.createCell((short) 9).setCellValue(nlyteCustomerDataTransStg.getWeightNm());
				row.createCell((short) 10).setCellValue(nlyteCustomerDataTransStg.getCopperPortsTx());
				row.createCell((short) 11).setCellValue(nlyteCustomerDataTransStg.getFiberPortsTx());
				row.createCell((short) 12).setCellValue(nlyteCustomerDataTransStg.getUndefPortsTx());
				row.createCell((short) 13).setCellValue(nlyteCustomerDataTransStg.getPowerConsmptNm());

				for (NltyeCustDataProcessBean master : masterList) {
					
					if (details.getnCustDataProcess().getNlyteCustomerDataTransStg().getNctId().equals( master
							.getnCustDataTransStg().getNctId())) {
						//System.out.println("Master Data Loop  "+details.getnCustDataProcess().getNlyteCustomerDataTransStg().getNctId().equals(master.getnCustDataTransStg().getNctId()));
						NlyteMasterDataTransStg nlyteMasterDataTransStg = master.getnMasterDataTrans();
						
						HSSFFont font= workBook.createFont();				       
				        font.setColor(IndexedColors.BLUE.getIndex());				        			                                    
				         // Create cell style 
				        CellStyle style=workBook.createCellStyle();    			        
				        // Setting font to style
				        style.setFont(font);				        
				        rowNo++;
						row = sheet.createRow(rowNo);
						Cell c0=row.createCell((short) 0);
						Cell c1=row.createCell((short) 1);
						Cell c2=row.createCell((short) 2);
						Cell c3=row.createCell((short) 3);
						Cell c4=row.createCell((short) 4);
						Cell c5=row.createCell((short) 5);
						Cell c6=row.createCell((short) 6);
						Cell c7=row.createCell((short) 7);
						Cell c8=row.createCell((short) 8);
						Cell c9=row.createCell((short) 9);
						Cell c10=row.createCell((short) 10);
						Cell c11=row.createCell((short) 11);
						Cell c12=row.createCell((short) 12);
						Cell c13=row.createCell((short) 13);
												
				        c0.setCellStyle(style);
						c0.setCellValue("");
						
						c1.setCellStyle(style);
						c1.setCellValue(nlyteMasterDataTransStg.getModelTx());
						
						c2.setCellStyle(style);
						c2.setCellValue(nlyteMasterDataTransStg.getMaterialNmTx());
						
						c3.setCellStyle(style);
						c3.setCellValue(nlyteMasterDataTransStg.getManufacturerTx());

						c4.setCellStyle(style);
						c4.setCellValue(nlyteMasterDataTransStg.getMaterialTypTx());
						
						c5.setCellStyle(style);
						c5.setCellValue(nlyteMasterDataTransStg.getMaterialSubTypTx());
						
						c6.setCellStyle(style);
						c6.setCellValue(nlyteMasterDataTransStg.getWidthNm());	
						
						c7.setCellStyle(style);
						c7.setCellValue(nlyteMasterDataTransStg.getDepthNm());	
						
						c8.setCellStyle(style);
						c8.setCellValue(nlyteMasterDataTransStg.getHeightNm());	
						
						c9.setCellStyle(style);
						c9.setCellValue(nlyteMasterDataTransStg.getWeightNm());	
						
						c10.setCellStyle(style);
						c10.setCellValue(nlyteMasterDataTransStg.getCopperPortsTx());
						
						c11.setCellStyle(style);
						c11.setCellValue(nlyteMasterDataTransStg.getFiberPortsTx());
						
						c12.setCellStyle(style);
						c12.setCellValue(nlyteMasterDataTransStg.getUndefPortsTx());
						
						c13.setCellStyle(style);
						c13.setCellValue(nlyteMasterDataTransStg.getPowerConsmptNm());
					}

				}
				rowNo++;
				sNo++;

			}
			String file = "MultiMatchedData_" + exportfile + ".xls";
			try {
				FileOutputStream fos = new FileOutputStream(file);
				workBook.write(fos);
				fos.flush();
				fos.close();
				HttpServletResponse response = ServletActionContext.getResponse();
				downloadFile(file, response);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}