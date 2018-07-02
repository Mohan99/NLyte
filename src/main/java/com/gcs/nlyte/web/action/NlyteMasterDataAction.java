package com.gcs.nlyte.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataTransStg;
import com.gcs.nlyte.web.persistance.dao.impl.NlyteCustomerDataStgDao;
import com.gcs.nlyte.web.servcie.INlyteService;
import com.opensymphony.xwork2.ActionContext;

public class NlyteMasterDataAction extends NLyteAction {
	// private static final String FILE_PATH =
	// "/C:/Users/sbhoompally/Desktop/Work/NLYTE/Materials - Mast.xlsx";
	/**
	 * 
	 */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NlyteMasterDataAction.class);
	private static final long serialVersionUID = -2178830877437319872L;
	private List<NlyteMasterDataStg> nlyteMasterDataList;
	private String filePath = null;
	// private Part part = null;
	// private Part fileNUpload;

	private File myFile;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public String getDestPath() {
		return destPath;
	}

	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}

	private String myFileContentType;
	private String myFileFileName;
	private String destPath;
	File destFile = null;

	public List<NlyteMasterDataStg> getNlyteMasterDataList() {
		return nlyteMasterDataList;
	}

	public void setNlyteMasterDataList(List<NlyteMasterDataStg> nlyteMasterDataList) {
		this.nlyteMasterDataList = nlyteMasterDataList;
	}

	private List<NlyteMasterDataTransStg> nlyteMastDtTransStgLst = new ArrayList<NlyteMasterDataTransStg>();
	private List<NlyteMasterDataTransStg> nlyteMastTransStgLstUpdate = new ArrayList<NlyteMasterDataTransStg>();

	public List<NlyteMasterDataTransStg> getNlyteMastTransStgLstUpdate() {
		return nlyteMastTransStgLstUpdate;
	}

	public void setNlyteMastTransStgLstUpdate(List<NlyteMasterDataTransStg> nlyteMastTransStgLstUpdate) {
		this.nlyteMastTransStgLstUpdate = nlyteMastTransStgLstUpdate;
	}

	public List<NlyteMasterDataTransStg> getNlyteMastDtTransStgLst() {
		return nlyteMastDtTransStgLst;
	}

	public void setNlyteMastDtTransStgLst(List<NlyteMasterDataTransStg> nlyteMastDtTransStgLst) {
		this.nlyteMastDtTransStgLst = nlyteMastDtTransStgLst;
	}

	public static String removeNPChars(String str) {
		while (str.indexOf("  ") >= 0) {
			str = str.replaceAll("  ", " ");
		}
		str = str.replaceAll("^\\s+", "");
		str = str.replaceAll("\\s+$", "");
		str = str.replaceAll("\\p{C}", "");
		str = str.replaceAll("[^\\x00-\\x7F]", "");
		str = str.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");
		return str;
	}

	@Override
	public String execute() {
		logger.info("execute() : NlyteMasterDataAction");
		try {
			// TODO Auto-generated method stub
			
			this.nlyteMasterDataList = this.nlyteService.getMasterDataInOredr();
			setNlyteMasterDataList(nlyteMasterDataList);

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}
	}

	public String excelMasterRead(int nmsId, INlyteService nlyteServiceObj, File destFile) {
		logger.info("excelMasterRead() : NlyteMasterDataAction");
		try {

			FileInputStream fis = new FileInputStream(destFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);

			sheet.setForceFormulaRecalculation(true);
			int rowNum = sheet.getLastRowNum() + 1;
			int colNum = sheet.getRow(0).getLastCellNum();
			// MATERIAL NUMBER
			// MATERIAL NAME

			int material_Num = -1, material_nm = -1, model = -1, manufacturer = -1, material_Typ = -1,
					materual_Sub_Typ = -1, copper_Ports = -1, fiber_Ports = -1, undefined_Ports = -1, width = -1,
					depth = -1, height = -1, weight = -1, power_Cons = -1;

			XSSFRow rowHeader = sheet.getRow(0);
			for (int j = 0; j < colNum; j++) {
				XSSFCell cell = rowHeader.getCell(j);
				String cellValue = cellToString(cell);

				if ("MATERIAL NUMBER".equalsIgnoreCase(cellValue)) {
					material_Num = j;
				} else if ("MATERIAL NAME".equalsIgnoreCase(cellValue)) {
					material_nm = j;
				} else if ("MODEL".equalsIgnoreCase(cellValue)) {
					model = j;
				} else if ("MANUFACTURER".equalsIgnoreCase(cellValue)) {
					manufacturer = j;
				} else if ("MATERIAL TYPE".equalsIgnoreCase(cellValue)) {
					material_Typ = j;
				} else if ("MATERIAL SUBTYPE".equalsIgnoreCase(cellValue)) {
					materual_Sub_Typ = j;
				} else if ("WIDTH".equalsIgnoreCase(cellValue)) {
					width = j;
				} else if ("DEPTH".equalsIgnoreCase(cellValue)) {
					depth = j;
				} else if ("HEIGHT".equalsIgnoreCase(cellValue)) {
					height = j;
				} else if ("WEIGHT".equalsIgnoreCase(cellValue)) {
					weight = j;
				} else if ("COPPER PORTS".equalsIgnoreCase(cellValue)) {
					copper_Ports = j;
				} else if ("FIBRE PORTS".equalsIgnoreCase(cellValue)) {
					fiber_Ports = j;
				} else if ("UNDEFINED PORTS".equalsIgnoreCase(cellValue)) {
					undefined_Ports = j;
				} else if ("POWER CONSUMTION".equalsIgnoreCase(cellValue)) {
					power_Cons = j;
				}
			}
			if (material_Num == -1 || material_nm == -1 || model == -1 || manufacturer == -1 || material_Typ == -1
					|| materual_Sub_Typ == -1 || width == -1 || height == -1 || weight == -1 || copper_Ports == -1
					|| fiber_Ports == -1 || undefined_Ports == -1 || power_Cons == -1) {
				try {

					throw new Exception(
							"Could not find header indexes\nempNo : " + model + " | managerName : " + model);
				} catch (Exception e) {

					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i = 1; i < rowNum; i++) {
				XSSFRow row = sheet.getRow(i);
				int nullCheck = 0;
				NlyteMasterDataStg mCds = new NlyteMasterDataStg();

				NlyteMasterDataTransStg stg = new NlyteMasterDataTransStg();
				try {
					if (row == null) {
						// System.out.println("row contains null data");
					} else {
						// stg.setModelTx(cellToString(row.getCell(model)));
						if (cellToString(row.getCell(model)).equals("0.0")) {
							nullCheck = 1;
							stg.setModelTx("null");
							continue;
						} else {
							stg.setModelTx(removeNPChars(cellToString(row.getCell(model))));
						}

						// stg.setMaterialNm((int)(row.getCell(material_Num).getNumericCellValue()));
						if (cellToString(row.getCell(material_Num)).equals("0.0")) {

							stg.setMaterialNm(0);
						} else {

							stg.setMaterialNm((int) (row.getCell(material_Num).getNumericCellValue()));
						}

						// stg.setMaterialNmTx(cellToString(row.getCell(material_nm)));
						if (cellToString(row.getCell(material_nm)).equals("0.0")) {
							nullCheck = 1;
							stg.setMaterialNmTx("null");
							continue;
						} else {

							stg.setMaterialNmTx(removeNPChars(cellToString(row.getCell(material_nm))));
						}

						// stg.setManufacturerTx(cellToString(row.getCell(manufacturer)));
						if (cellToString(row.getCell(manufacturer)).equals("0.0")) {
							stg.setManufacturerTx("null");
						} else {
							stg.setManufacturerTx(cellToString(row.getCell(manufacturer)));
						}

						// stg.setMaterialTypTx(cellToString(row.getCell(material_Typ)));
						if (cellToString(row.getCell(material_Typ)).equals("0.0")) {
							stg.setMaterialTypTx("null");
						} else {
							stg.setMaterialTypTx(cellToString(row.getCell(material_Typ)));
						}

						// stg.setMaterialSubTypTx(cellToString(row.getCell(materual_Sub_Typ)));
						if (cellToString(row.getCell(materual_Sub_Typ)).equals("0.0")) {
							stg.setMaterialSubTypTx("null");
						} else {
							stg.setMaterialSubTypTx(cellToString(row.getCell(materual_Sub_Typ)));
						}

						String widthNm = cellToString(row.getCell(width));
						if (widthNm.contains("NULL")) {
							stg.setWidthNm((float) 0.0);
						} else {
							stg.setWidthNm(Float.parseFloat(widthNm));
						}

						// stg.setDepthNm((float) row.getCell(depth).getNumericCellValue());
						String depthNm = cellToString(row.getCell(depth));
						if (depthNm.contains("NULL")) {
							stg.setDepthNm((float) 0.0);
						} else {
							stg.setDepthNm(Float.parseFloat(depthNm));
						}

						// stg.setHeightNm((float) row.getCell(height).getNumericCellValue());
						String heightNm = cellToString(row.getCell(height));
						if (heightNm.contains("NULL")) {
							stg.setHeightNm((float) 0.0);
						} else {
							stg.setHeightNm(Float.parseFloat(heightNm));
						}

						// stg.setWeightNm((float) row.getCell(weight).getNumericCellValue());
						String weightNm = cellToString(row.getCell(weight));
						if (weightNm.contains("NULL")) {
							stg.setWeightNm((float) 0.0);
						} else {
							stg.setWeightNm(Float.parseFloat(weightNm));
						}

						// stg.setPowerConsmptNm((float) row.getCell(power_Cons).getNumericCellValue());
						String powerCnp = cellToString(row.getCell(power_Cons));
						if (powerCnp.contains("NULL")) {
							stg.setPowerConsmptNm((float) 0.0);
						} else {
							stg.setPowerConsmptNm(Float.parseFloat(powerCnp));
						}

						// stg.setCopperPortsTx(cellToString(row.getCell(copper_Ports)));
						if (cellToString(row.getCell(copper_Ports)).equals("0.0")) {
							stg.setCopperPortsTx("0");
						} else {
							stg.setCopperPortsTx(cellToString(row.getCell(copper_Ports)));
						}

						// stg.setFiberPortsTx(cellToString(row.getCell(fiber_Ports)));
						if (cellToString(row.getCell(fiber_Ports)).equals("0.0")) {
							stg.setFiberPortsTx("0");
						} else {
							stg.setFiberPortsTx(cellToString(row.getCell(fiber_Ports)));
						}

						// stg.setUndefPortsTx(cellToString(row.getCell(undefined_Ports)));
						if (cellToString(row.getCell(undefined_Ports)).equals("0.0")) {
							stg.setUndefPortsTx("0");
						} else {
							stg.setUndefPortsTx(cellToString(row.getCell(undefined_Ports)));
						}

						stg.setCreatedDt(new Date());
						stg.setUpdatedDt(new Date());
						stg.setActiveYn(true);
						// stg.setNmsId(null);
						mCds.setNmsId(nmsId);
						stg.setNlyteMasterDataStg(mCds);
						// stg.setCmsId(cmsId);
						if (nullCheck == 0)
							nlyteMastDtTransStgLst.add(stg);
					}

				} catch (Exception e) {
					// double d = Double.parseDouble(cellToString(row.getCell(material_Num)));
					// stg.setModelTx((int) d);
					System.out.println("Model....." + removeNPChars(cellToString(row.getCell(model))));
					System.out.println("Material Name....." + removeNPChars(cellToString(row.getCell(material_nm))));
					e.printStackTrace();
					StringWriter errors = new StringWriter();
					logger.error("Exception Message: " + errors.toString());
				}

			}

			boolean flag = Boolean.FALSE;
			if (nlyteMastDtTransStgLst.size() > 0) {
				flag = nlyteServiceObj.nlyteMasterImportEachCell(nlyteMastDtTransStgLst);
				return "sucess";
			}
			fis.close();

		} catch (IOException e) {

			e.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}
		return "sucess";
	}

	public static String cellToString(XSSFCell cell) {

		int type;
		Object result = null;

		if (cell == null) {
			return "0.0";
		} else if (cell.toString() == "#N/A") {
			return "0.0";
		} else {
			type = cell.getCellType();
			switch (type) {

			case XSSFCell.CELL_TYPE_NUMERIC:
				result = BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();

				break;
			case XSSFCell.CELL_TYPE_STRING:
				result = cell.getStringCellValue();
				break;
			case XSSFCell.CELL_TYPE_BLANK:
				result = "0.0";
				break;
			case XSSFCell.CELL_TYPE_FORMULA:
				result = cell.getCellFormula();

			}
			return result.toString();
		}
	}

	public static Double cellToDouble(XSSFCell cell) {

		int type;
		Object result = null;
		type = cell.getCellType();
		switch (type) {

		case XSSFCell.CELL_TYPE_NUMERIC:
			result = cell.getNumericCellValue();

			break;
		case XSSFCell.CELL_TYPE_STRING:
			result = cell.getStringCellValue();
			break;
		case XSSFCell.CELL_TYPE_BLANK:
			result = "0.0";
			break;
		case XSSFCell.CELL_TYPE_FORMULA:
			result = cell.getCellFormula();
		}
		return (Double) result;
	}

	public String nlyteMasterList() {
		logger.info("execute() : NlyteMasterDataAction");
		List<NlyteMasterDataTransStg> nlyteMastDtTrnsStg = this.nlyteService.getnlyteMasterList();
		setNlyteMastDtTransStgLst(nlyteMastDtTrnsStg);
		return "success";
	}

	public String excelMasterReadInsUpdate(int nmsId, INlyteService nlyteServiceObj, File destFile) {
		logger.info("excelMasterReadInsUpdate() : NlyteMasterDataAction");
		try {
			List<NlyteMasterDataTransStg> nlyteMasterDataTransStg = nlyteServiceObj.getnlyteMasterList();

			FileInputStream fis = new FileInputStream(destFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			sheet.setForceFormulaRecalculation(true);
			int rowNum = sheet.getLastRowNum() + 1;
			int colNum = sheet.getRow(0).getLastCellNum();
			int material_Num = -1, material_nm = -1, model = -1, manufacturer = -1, material_Typ = -1,
					materual_Sub_Typ = -1, copper_Ports = -1, fiber_Ports = -1, undefined_Ports = -1, width = -1,
					depth = -1, height = -1, weight = -1, power_Cons = -1;
			XSSFRow rowHeader = sheet.getRow(0);
			for (int j = 0; j < colNum; j++) {
				// System.out.println("Line 5");
				XSSFCell cell = rowHeader.getCell(j);
				String cellValue = cellToString(cell);

				if ("MATERIAL NUMBER".equalsIgnoreCase(cellValue)) {
					material_Num = j;
				} else if ("MATERIAL NAME".equalsIgnoreCase(cellValue)) {
					material_nm = j;
				} else if ("MODEL".equalsIgnoreCase(cellValue)) {
					model = j;
				} else if ("MANUFACTURER".equalsIgnoreCase(cellValue)) {
					manufacturer = j;
				} else if ("MATERIAL TYPE".equalsIgnoreCase(cellValue)) {
					material_Typ = j;
				} else if ("MATERIAL SUBTYPE".equalsIgnoreCase(cellValue)) {
					materual_Sub_Typ = j;
				} else if ("WIDTH".equalsIgnoreCase(cellValue)) {
					width = j;
				} else if ("DEPTH".equalsIgnoreCase(cellValue)) {
					depth = j;
				} else if ("HEIGHT".equalsIgnoreCase(cellValue)) {
					height = j;
				} else if ("WEIGHT".equalsIgnoreCase(cellValue)) {
					weight = j;
				} else if ("COPPER PORTS".equalsIgnoreCase(cellValue)) {
					copper_Ports = j;
				} else if ("FIBRE PORTS".equalsIgnoreCase(cellValue)) {
					fiber_Ports = j;
				} else if ("UNDEFINED PORTS".equalsIgnoreCase(cellValue)) {
					undefined_Ports = j;
				} else if ("POWER CONSUMTION".equalsIgnoreCase(cellValue)) {
					power_Cons = j;
				}
				// System.out.println("Line 6");
			}
			if (material_Num == -1 || material_nm == -1 || model == -1 || manufacturer == -1 || material_Typ == -1
					|| materual_Sub_Typ == -1 || width == -1 || height == -1 || weight == -1 || copper_Ports == -1
					|| fiber_Ports == -1 || undefined_Ports == -1 || power_Cons == -1) {
				try {
					throw new Exception(
							"Could not find header indexes\nempNo : " + model + " | managerName : " + model);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			for (int i = 1; i < rowNum; i++) {
				int nullCheck = 0;
				// System.out.println("Line 9");
				XSSFRow row = sheet.getRow(i);
				if (row == null) {

				} else {
					NlyteMasterDataStg mCds = new NlyteMasterDataStg();

					NlyteMasterDataTransStg stg = new NlyteMasterDataTransStg();
					try {

					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					if (cellToString(row.getCell(model)).equals("0.0")) {
						nullCheck = 1;
						stg.setModelTx("null");
						continue;
					} else {
						stg.setModelTx(cellToString(row.getCell(model)));
					}

					// stg.setMaterialNm((int)(row.getCell(material_Num).getNumericCellValue()));
					if (cellToString(row.getCell(material_Num)).equals("0.0")) {

						stg.setMaterialNm(0);

					} else {
						stg.setMaterialNm((int) (row.getCell(material_Num).getNumericCellValue()));
					}

					// stg.setMaterialNmTx(cellToString(row.getCell(material_nm)));
					if (cellToString(row.getCell(material_nm)).equals("0.0")) {
						nullCheck = 1;
						stg.setMaterialNmTx("null");
						continue;
					} else {
						stg.setMaterialNmTx(cellToString(row.getCell(material_nm)));
					}

					// stg.setManufacturerTx(cellToString(row.getCell(manufacturer)));
					if (cellToString(row.getCell(manufacturer)).equals("0.0")) {
						stg.setManufacturerTx("null");
					} else {
						stg.setManufacturerTx(cellToString(row.getCell(manufacturer)));
					}

					// stg.setMaterialTypTx(cellToString(row.getCell(material_Typ)));
					if (cellToString(row.getCell(material_Typ)).equals("0.0")) {
						stg.setMaterialTypTx("null");
					} else {
						stg.setMaterialTypTx(cellToString(row.getCell(material_Typ)));
					}

					// stg.setMaterialSubTypTx(cellToString(row.getCell(materual_Sub_Typ)));
					if (cellToString(row.getCell(materual_Sub_Typ)).equals("0.0")) {
						stg.setMaterialSubTypTx("null");
					} else {
						stg.setMaterialSubTypTx(cellToString(row.getCell(materual_Sub_Typ)));
					}

					// stg.setWidthNm((float) (row.getCell(width).getNumericCellValue()));
					String widthNm = cellToString(row.getCell(width));
					if (widthNm.contains("NULL")) {
						stg.setWidthNm((float) 0.0);
					} else {
						stg.setWidthNm(Float.parseFloat(widthNm));
					}

					// stg.setDepthNm((float) row.getCell(depth).getNumericCellValue());
					String depthNm = cellToString(row.getCell(depth));
					if (depthNm.contains("NULL")) {
						stg.setDepthNm((float) 0.0);
					} else {
						stg.setDepthNm(Float.parseFloat(depthNm));
					}

					// stg.setHeightNm((float) row.getCell(height).getNumericCellValue());
					String heightNm = cellToString(row.getCell(height));
					if (heightNm.contains("NULL")) {
						stg.setHeightNm((float) 0.0);
					} else {
						stg.setHeightNm(Float.parseFloat(heightNm));
					}

					// stg.setWeightNm((float) row.getCell(weight).getNumericCellValue());
					String weightNm = cellToString(row.getCell(weight));
					if (weightNm.contains("NULL")) {
						stg.setWeightNm((float) 0.0);
					} else {
						stg.setWeightNm(Float.parseFloat(weightNm));
					}

					// stg.setPowerConsmptNm((float) row.getCell(power_Cons).getNumericCellValue());
					String powerCnp = cellToString(row.getCell(power_Cons));
					if (powerCnp.contains("NULL")) {
						stg.setPowerConsmptNm((float) 0.0);
					} else {
						stg.setPowerConsmptNm(Float.parseFloat(powerCnp));
					}

					/*
					 * stg.setCopperPortsTx(cellToString(row.getCell(copper_Ports)));
					 * stg.setFiberPortsTx(cellToString(row.getCell(fiber_Ports)));
					 * stg.setUndefPortsTx(cellToString(row.getCell(undefined_Ports)));
					 */
					if (cellToString(row.getCell(copper_Ports)).equals("0.0")) {
						stg.setCopperPortsTx("0");
					} else {
						stg.setCopperPortsTx(cellToString(row.getCell(copper_Ports)));
					}

					// stg.setFiberPortsTx(cellToString(row.getCell(fiber_Ports)));
					if (cellToString(row.getCell(fiber_Ports)).equals("0.0")) {
						stg.setFiberPortsTx("0");
					} else {
						stg.setFiberPortsTx(cellToString(row.getCell(fiber_Ports)));
					}

					// stg.setUndefPortsTx(cellToString(row.getCell(undefined_Ports)));
					if (cellToString(row.getCell(undefined_Ports)).equals("0.0")) {
						stg.setUndefPortsTx("0");
					} else {
						stg.setUndefPortsTx(cellToString(row.getCell(undefined_Ports)));
					}

					stg.setActiveYn(true);
					mCds.setNmsId(nmsId);
					stg.setNlyteMasterDataStg(mCds);

					/*int found = compareMasterData(nlyteMasterDataTransStg, stg.getMaterialNm(), stg.getMaterialNmTx(),
							stg.getModelTx(), stg.getManufacturerTx());*/
					
					int found=nlyteServiceObj.matchMasterData(stg.getMaterialNmTx(), stg.getModelTx(), stg.getManufacturerTx());
					
					if (found > 0) // adding the record to update the record
					{
						stg.setNmtId(found);
						stg.setUpdatedDt(new Date());
						stg.setCreatedDt(new Date());
						nlyteMastTransStgLstUpdate.add(stg);
						// System.out.println("adding the records to update method size
						// ="+nlyteMastTransStgLstUpdate.size());

					} else // record is not found so inserting the new record into master trans data table
					{
						// System.out.println("adding the records to insert method");
						stg.setCreatedDt(new Date());
						stg.setUpdatedDt(new Date());

						nlyteMastDtTransStgLst.add(stg);
					}
				}
			}

			boolean insflag = Boolean.FALSE;
			boolean updateflag = Boolean.FALSE;

			if (nlyteMastDtTransStgLst.size() > 0) {
				// System.out.println("calling the insert method with the records size
				// ="+nlyteMastDtTransStgLst.size());
				insflag = nlyteServiceObj.nlyteMasterImportEachCell(nlyteMastDtTransStgLst);
			}
			if (nlyteMastTransStgLstUpdate.size() > 0) {
				// System.out.println("calling the update method with the records size
				// ="+nlyteMastTransStgLstUpdate.size());
				updateflag = nlyteServiceObj.nlyteMasterImportUpdateCell(nlyteMastTransStgLstUpdate);
			}
			fis.close();
			// System.out.println("Line 15");
		} catch (IOException e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}
		return "sucess";
	}

	public int compareMasterData(List<NlyteMasterDataTransStg> prevList, int MaterialNm, String MaterialNmTx,
			String ModelTx, String ManufacturerTx) {
		// logger.info("compareMasterData() : NlyteMasterDataAction");
		int found = 0;
		// System.out.println("********** Inside compareMasterData method size of
		// existing master trans data ="+prevList.size());
		for (NlyteMasterDataTransStg prevListdata : prevList) {

			if ((prevListdata.getMaterialNmTx().equals(MaterialNmTx))
					&& (prevListdata.getModelTx().equals(ModelTx))
					&& (prevListdata.getManufacturerTx().equals(ManufacturerTx))) {
				// System.out.println("********** Condition became true now making found to true
				// to update the records");
				found = prevListdata.getNmtId();
			}
		}
		// System.out.println("**********found value before returning "+found);

		return found;
	}
}
