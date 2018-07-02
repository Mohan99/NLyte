package com.gcs.nlyte.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataTransStg;
import com.gcs.nlyte.web.servcie.INlyteService;

public class NlyteCustImportStgAction extends NLyteAction {
	private static org.apache.log4j.Logger logger =
	           org.apache.log4j.Logger.getLogger(NlyteCustImportStgAction.class);
	

	private List<NlyteCustomerDataTransStg> nlyteCustDtTransStgLst = new ArrayList<NlyteCustomerDataTransStg>();

	public List<NlyteCustomerDataTransStg> getNlyteCustDtTransStgLst() {
		return nlyteCustDtTransStgLst;
	}

	public void setNlyteCustDtTransStgLst(List<NlyteCustomerDataTransStg> nlyteCustDtTransStgLst) {
		this.nlyteCustDtTransStgLst = nlyteCustDtTransStgLst;
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
	
	public String excelRead(int cmsId, INlyteService nlyteServiceObj, File destFile) {
		logger.info("excelRead() : NlyteCustImportStgAction");

		try {
			
			FileInputStream fis = new FileInputStream(destFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			sheet.setForceFormulaRecalculation(true);
			int rowNum = sheet.getLastRowNum() + 1;
			int colNum = sheet.getRow(0).getLastCellNum();

			int model = -1, manufacturer = -1, material_Typ = -1, materual_Sub_Typ = -1, copper_Ports = -1,
					fiber_Ports = -1, undefined_Ports = -1, width = -1, depth = -1, height = -1, weight = -1,
					power_Cons = -1;
			// Read the headers first. Locate the ones you need
			XSSFRow rowHeader = sheet.getRow(0);
			for (int j = 0; j < colNum; j++) {
				XSSFCell cell = rowHeader.getCell(j);
				String cellValue = cellToString(cell);

				if ("MODEL".equalsIgnoreCase(cellValue)) {
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
			if (model == -1 || manufacturer == -1 || material_Typ == -1 || materual_Sub_Typ == -1 || width == -1
					|| height == -1 || weight == -1 || copper_Ports == -1 || fiber_Ports == -1 || undefined_Ports == -1
					|| power_Cons == -1) {
				try {
					throw new Exception(
							"Could not find header indexes\nempNo : " + model + " | managerName : " + model);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			for (int i = 1; i < rowNum; i++) {
				int nullCheck = 0;
				XSSFRow row = sheet.getRow(i);
				NlyteCustomerDataStg nCds = new NlyteCustomerDataStg();

				NlyteCustomerDataTransStg stg = new NlyteCustomerDataTransStg();
				if(row==null)
				{
					//System.out.println("row contains null data");
				}
				else
				{
				if (cellToString(row.getCell(model)).equals("0.0")){
					nullCheck = 1;
					stg.setModelTx("null");
				} else {	
					stg.setModelTx(removeNPChars(cellToString(row.getCell(model))));
				}

				if (cellToString(row.getCell(manufacturer)).equals("0.0")) {
					stg.setManufacturerTx("null");
				} else {
					stg.setManufacturerTx(removeNPChars(cellToString(row.getCell(manufacturer))));
				}

				if (cellToString(row.getCell(material_Typ)).equals("0.0")) {
					stg.setMaterialTypTx("null");
				} else {
					stg.setMaterialTypTx(cellToString(row.getCell(material_Typ)));
				}

				if (cellToString(row.getCell(materual_Sub_Typ)).equals("0.0")) {
					stg.setMaterialSubTypTx("null");
				} else {
					stg.setMaterialSubTypTx(cellToString(row.getCell(materual_Sub_Typ)));
				}

				/*
				 * stg.setModelTx(cellToString(row.getCell(model)));
				 * stg.setManufacturerTx(cellToString(row.getCell(manufacturer)));
				 * stg.setMaterialTypTx(cellToString(row.getCell(material_Typ)));
				 * stg.setMaterialSubTypTx(cellToString(row.getCell(materual_Sub_Typ)));
				 */

				/*
				 * stg.setWidthNm((float) (row.getCell(width).getNumericCellValue()));
				 * stg.setDepthNm((float) row.getCell(depth).getNumericCellValue());
				 * stg.setHeightNm((float) row.getCell(height).getNumericCellValue());
				 * stg.setWeightNm((float) row.getCell(weight).getNumericCellValue());
				 */

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
				if (cellToString(row.getCell(undefined_Ports)).equals("0.0")
						|| cellToString(row.getCell(undefined_Ports)).contains("NULL")) {
					stg.setUndefPortsTx("0");
				} else {
					stg.setUndefPortsTx(cellToString(row.getCell(undefined_Ports)));
				}

				// stg.setPowerConsmptNm((float) row.getCell(power_Cons).getNumericCellValue());
				String powerCnp = cellToString(row.getCell(power_Cons));
				if (powerCnp.contains("NULL")) {
					stg.setPowerConsmptNm((float) 0.0);
				} else {
					stg.setPowerConsmptNm(Float.parseFloat(powerCnp));
				}
				stg.setCreatedDt(new Date());
				stg.setUpdatedDt(new Date());
				stg.setValidYn(true);
				stg.setNmsValue(null);
				stg.setActiveYn(true);
				stg.setNmsId(null);
				nCds.setCmsId(cmsId);
				stg.setNlyteCustomerDataStg(nCds);
				// stg.setCmsId(cmsId);
				if (nullCheck == 0)
					nlyteCustDtTransStgLst.add(stg);
			}
			}
			boolean flag = Boolean.FALSE;
			if (nlyteCustDtTransStgLst.size() > 0) {
				flag = nlyteServiceObj.nlyteCustomerImportEachCell(nlyteCustDtTransStgLst);
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

	public static Float cellToFloat(XSSFCell cell) {

		int type;
		Object result = null;
		if (cell == null) {
			return (float) 0.0;
		} else {
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
			return (Float) result;
		}
	}

	public String nlyteCustomerList() {
		logger.info("nlyteCustomerList() : NlyteCustImportStgAction");
		try {
		List<NlyteCustomerDataTransStg> nlyteCustDtTrnsStg = this.nlyteService.getnlyteCustomerList();
		setNlyteCustDtTransStgLst(nlyteCustDtTrnsStg);
		return "success";
		}catch(Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}
	}

	public String processData() {
		return "success";
	}
}
