package com.gcs.nlyte.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataTransStg;

public class NlyteMasterTransDataViewAction extends NLyteAction {
	private static org.apache.log4j.Logger logger =
	           org.apache.log4j.Logger.getLogger(NlyteMasterTransDataViewAction.class);
	private static final long serialVersionUID = 1L;
	private String nmsId;

	public String getNmsId() {
		return nmsId;
	}

	public void setNmsId(String nmsId) {
		this.nmsId = nmsId;
	}

	private List<NlyteMasterDataTransStg> nlyteMasterDataTransStg = new ArrayList<NlyteMasterDataTransStg>();

	public List<NlyteMasterDataTransStg> getNlyteMasterDataTransStg() {
		return nlyteMasterDataTransStg;
	}

	public void setNlyteMasterDataTransStg(List<NlyteMasterDataTransStg> nlyteCustDataTransStg) {
		this.nlyteMasterDataTransStg = nlyteCustDataTransStg;
	}

	@Override
	public String execute() {
		logger.info("execute() : NlyteMasterTransDataViewAction");
		boolean flag = Boolean.FALSE;
		try {
			List<NlyteMasterDataTransStg> nlyteMasterDtTransUpdated = this.nlyteService
					.getMasterDataTransStgList(Integer.parseInt(getNmsId()));

			setNlyteMasterDataTransStg(nlyteMasterDtTransUpdated);
			return "success";
		} catch (Exception ex) {
			ex.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}

	}

	public String transMasterDataExport() {
		logger.info("transMasterDataExport() : NlyteMasterTransDataViewAction");
		try {
			List<NlyteMasterDataTransStg> nlyteMasterData = this.nlyteService.getnlyteMasterList();
			setNlyteMasterDataTransStg(nlyteMasterData);
			if (nlyteMasterData != null && nlyteMasterData.size() > 0) {
				doExport(nlyteMasterData);
				return "success";
			} else {
				return "error";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";

		}

	}

	public void doExport(List<NlyteMasterDataTransStg> dataList) throws Exception {
		logger.info("doExport() : NlyteMasterTransDataViewAction");
		try {
			if (dataList != null && !dataList.isEmpty()) {
				HSSFWorkbook workBook = new HSSFWorkbook();
				HSSFSheet sheet = workBook.createSheet();
				HSSFRow headingRow = sheet.createRow(0);
				headingRow.createCell((short) 0).setCellValue("MATERIAL NUMBER");
				headingRow.createCell((short) 1).setCellValue("MATERIAL NAME");
				headingRow.createCell((short) 2).setCellValue("MODEL");
				headingRow.createCell((short) 3).setCellValue("MANUFACTURER");
				headingRow.createCell((short) 4).setCellValue("MATERIAL TYPE");
				headingRow.createCell((short) 5).setCellValue("MATERIAL SUBTYPE");

				headingRow.createCell((short) 6).setCellValue("WIDTH");
				headingRow.createCell((short) 7).setCellValue("DEPTH");
				headingRow.createCell((short) 8).setCellValue("HEIGHT");
				headingRow.createCell((short) 9).setCellValue("WEIGHT");

				headingRow.createCell((short) 10).setCellValue("COPPER PORTS");
				headingRow.createCell((short) 11).setCellValue("FIBRE PORTS");
				headingRow.createCell((short) 12).setCellValue("UNDEFINED PORTS");

				headingRow.createCell((short) 13).setCellValue("POWER CONSUMPTION");

				short rowNo = 1;
				for (NlyteMasterDataTransStg details : dataList) {
					HSSFRow row = sheet.createRow(rowNo);
					row.createCell((short) 0).setCellValue(rowNo);
					row.createCell((short) 1).setCellValue(details.getMaterialNmTx());
					row.createCell((short) 2).setCellValue(details.getModelTx());
					row.createCell((short) 3).setCellValue(details.getManufacturerTx());
					row.createCell((short) 4).setCellValue(details.getMaterialTypTx());
					row.createCell((short) 5).setCellValue(details.getMaterialSubTypTx());
					row.createCell((short) 6).setCellValue(details.getWidthNm());
					row.createCell((short) 7).setCellValue(details.getDepthNm());
					row.createCell((short) 8).setCellValue(details.getHeightNm());
					row.createCell((short) 9).setCellValue(details.getWeightNm());
					row.createCell((short) 10).setCellValue(details.getUndefPortsTx());
					row.createCell((short) 11).setCellValue(details.getCopperPortsTx());
					row.createCell((short) 12).setCellValue(details.getFiberPortsTx());
					row.createCell((short) 13).setCellValue(details.getPowerConsmptNm());
					rowNo++;
				}

				String file = "MaterialsMasterData.xlsx";
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
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
		}
	}

	private void downloadFile(final String fileName, HttpServletResponse response) {
		logger.info("downloadFile() : NlyteMasterTransDataViewAction");
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
}
