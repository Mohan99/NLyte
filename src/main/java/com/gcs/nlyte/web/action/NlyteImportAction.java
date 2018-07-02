package com.gcs.nlyte.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataTransStg;

public class NlyteImportAction extends NLyteAction {
	/**
	 * 
	 */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NlyteImportAction.class);
	private static final long serialVersionUID = -3129432007068338512L;

	private File fileNUpload;
	private String fileNUploadContentType;
	private File myFile;
	private String filePath = null;
	private String mastHeaderCheck;
	private String mastSheetCheck;
	private String mastDataCheck;

	public String getMastSheetCheck() {
		return mastSheetCheck;
	}

	public void setMastSheetCheck(String mastSheetCheck) {
		this.mastSheetCheck = mastSheetCheck;
	}

	public String getMastDataCheck() {
		return mastDataCheck;
	}

	public void setMastDataCheck(String mastDataCheck) {
		this.mastDataCheck = mastDataCheck;
	}

	public String getMastHeaderCheck() {
		return mastHeaderCheck;
	}

	public void setMastHeaderCheck(String mastHeaderCheck) {
		this.mastHeaderCheck = mastHeaderCheck;
	}

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

	private String fileNUploadFileName;
	@Autowired
	private HttpServletRequest request;
	private List<NlyteMasterDataStg> nlyteMasterDataList;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	private List<NlyteMasterDataStg> nlyteMastDataStgLst = new ArrayList<NlyteMasterDataStg>();

	public File getFileNUpload() {
		return fileNUpload;
	}

	public void setFileNUpload(File fileNUpload) {
		this.fileNUpload = fileNUpload;
	}

	public String getFileNUploadContentType() {
		return fileNUploadContentType;
	}

	public void setFileNUploadContentType(String fileNUploadContentType) {
		this.fileNUploadContentType = fileNUploadContentType;
	}

	public String getFileNUploadFileName() {
		return fileNUploadFileName;
	}

	public void setFileNUploadFileName(String fileNUploadFileName) {
		this.fileNUploadFileName = fileNUploadFileName;
	}

	/**
	 * 
	 */

	public List<NlyteMasterDataStg> getNlyteMastDataStgLst() {
		return nlyteMastDataStgLst;
	}

	public void setNlyteMastDataStgLst(List<NlyteMasterDataStg> nlyteMastDataStgLst) {
		this.nlyteMastDataStgLst = nlyteMastDataStgLst;
	}

	@Override
	public String execute() throws Exception {
		/* Copy file to a safe location */
		destPath = "./";

		return "success";
	}

	public String nlyteImportView1() {
		logger.info("nlyteImportView1() : NlyteImportAction");
		this.nlyteMasterDataList = this.nlyteService.getMasterData();
		setNlyteMasterDataList(nlyteMasterDataList);
		return "success";
	}

	public String display() {
		return "error";
	}

	public String nlyteImportExcel() {

		return "nlyteImportReq";
	}

	public String nlyteImportView() {

		return "success";
	}

	public String nlyteImportCall() {
		logger.info("nlyteImportCall() : NlyteImportAction");
		boolean flag = Boolean.FALSE;
		/* Copy file to a safe location */
		destPath = "./";

		try {
			destFile = new File(destPath, myFileFileName);
			FileUtils.copyFile(myFile, destFile);

		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
		try {
			this.nlyteMasterDataList = this.nlyteService.getMasterDataInOredr();
			setNlyteMasterDataList(nlyteMasterDataList);

			int chkHeader = getHeaderChech(destFile);
			
			//if (chkHeader == 0 && getMastSheetCheck() == null && getMastDataCheck() == null) {
				if (chkHeader == 0 && getMastDataCheck() == null) {
				if (nlyteMasterDataList.size() > 0) {
					int masterId = 0;
					boolean fileFlag = false;
					for (NlyteMasterDataStg mastListdata : nlyteMasterDataList) {
						if (mastListdata.getDescriptionTx().equals((myFileFileName))) {
							fileFlag = true;
							masterId = mastListdata.getNmsId();

						}
					}
					if (fileFlag && masterId > 0) {
						nlyteUpdateData(nlyteMasterDataList, destFile, masterId);
					} else {
						if (myFile != null) {
							NlyteMasterDataStg stg = new NlyteMasterDataStg();
							// this.user.getNlyteUserId();
							String userId = this.user.getLoginId();
							String displayName = this.user.getUserFirstNameTx().toString() + ","
									+ this.user.getUserLastNameTx().toString();
							stg.setDescriptionTx(getMyFileFileName());
							stg.setLstUpdtDtm(new Date());
							stg.setCreatedDt(new Date());
							stg.setActiveYn(true);
							stg.setLoginId(userId);
							stg.setUserDisplayNameTx(displayName);
							stg.setStatus("N");
							stg.setUniqueId(UUID.randomUUID().toString());
							byte[] bytesArray = new byte[(int) myFile.length()];
							FileInputStream fis = new FileInputStream(myFile);
							fis.read(bytesArray);
							fis.close();
							stg.setExcelFileBlb(bytesArray);
							// if (nlyteMasterDataList.size() == 0)
							flag = this.nlyteService.nlyteImportCall(stg);

							NlyteMasterDataStg m = new NlyteMasterDataStg();

							m = this.nlyteService.getMasterPrimaryDataByUUID(stg.getUniqueId());

							if (stg != null && null != stg.getNmsId()) {
								NlyteMasterDataAction nlyteImptStgAct = new NlyteMasterDataAction();
								// String s = nlyteImptStgAct.excelMasterRead(m.getNmsId(), nlyteService,
								// destFile);
								nlyteUpdateData(nlyteMasterDataList, destFile, m.getNmsId());
							}
						}

					}
					// call a method which does the business logic of updating the master
					// transactional data .
					// for loop ending commented}
				} else {
					if (myFile != null) {
						NlyteMasterDataStg stg = new NlyteMasterDataStg();
						// this.user.getNlyteUserId();
						String userId = this.user.getLoginId();
						String displayName = this.user.getUserFirstNameTx().toString() + ","
								+ this.user.getUserLastNameTx().toString();
						stg.setDescriptionTx(getMyFileFileName());
						stg.setLstUpdtDtm(new Date());
						stg.setCreatedDt(new Date());
						stg.setActiveYn(true);
						stg.setLoginId(userId);
						stg.setUserDisplayNameTx(displayName);
						stg.setStatus("N");
						stg.setUniqueId(UUID.randomUUID().toString());
						byte[] bytesArray = new byte[(int) myFile.length()];
						FileInputStream fis = new FileInputStream(myFile);
						fis.read(bytesArray);
						fis.close();
						stg.setExcelFileBlb(bytesArray);
						if (nlyteMasterDataList.size() == 0)
							flag = this.nlyteService.nlyteImportCall(stg);

						NlyteMasterDataStg m = new NlyteMasterDataStg();

						m = this.nlyteService.getMasterPrimaryDataByUUID(stg.getUniqueId());
						
						if (stg != null && null != stg.getNmsId()) {
							NlyteMasterDataAction nlyteImptStgAct = new NlyteMasterDataAction();
							String s = nlyteImptStgAct.excelMasterRead(m.getNmsId(), nlyteService, destFile);
						}
					}

				}
				this.nlyteMasterDataList = this.nlyteService.getMasterDataInOredr();
				setNlyteMasterDataList(nlyteMasterDataList);
			} else if (chkHeader == 1) {
				setMastHeaderCheck(String.valueOf(chkHeader));
				return "error";
			} else {
				return "error";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}
		if (flag) {
			return "success";
		} else {
			return "success";
		}
	}

	public String nlyteImportList(List<NlyteMasterDataStg> nlyteMasterDataList) {
		logger.info("nlyteImportList() : NlyteImportAction");
		List<NlyteMasterDataStg> nlyteMastDtStg = this.nlyteService.getMasterDtStgImportList();
		setNlyteMastDataStgLst(nlyteMastDtStg);
		return "success";
	}

	public void nlyteUpdateData(List<NlyteMasterDataStg> nlyteMasterDataList, File destFile, int masterId) {
		logger.info("nlyteUpdateData() : NlyteImportAction");
		try {
			NlyteMasterDataAction nlyteImptStgAct = new NlyteMasterDataAction();
			String s = nlyteImptStgAct.excelMasterReadInsUpdate(masterId, nlyteService, destFile);
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
		}

	}

	public int getHeaderChech(File destFile) {
		logger.info("nlyteImportView1() : NlyteImportAction");
		int chkHd = 0;
		try {
			FileInputStream fis = new FileInputStream(destFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			if (workbook.getNumberOfSheets() > 1)
				setMastSheetCheck("1");
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
				chkHd = 1;
			}
			int nullCheck = 0;
			int rowVal = 0;
			for (int i = 1; i < rowNum; i++) {
				XSSFRow row = sheet.getRow(i);
				if (row != null) {

					if (cellToString(row.getCell(material_nm)).equals("0.0")
							|| cellToString(row.getCell(model)).equals("0.0")) {
						nullCheck++;
					}
					rowVal++;
				}
			}
			if (rowVal == nullCheck) {
				
				setMastDataCheck("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
		}

		return chkHd;

	}

	public static String cellToString(XSSFCell cell) {
		

		int type;
		Object result = null;
		if (cell == null) {
			// System.out.println("Cell...." + cell);
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

}
