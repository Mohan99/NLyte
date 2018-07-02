package com.gcs.nlyte.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor.ToBytesTransformer;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gcs.nlyte.web.bean.NlyteProcessedCountBean;

//import javax.servlet.http.HttpSession;

/*import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;*/

import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteUsers;

public class NlyteCustImportAction extends NLyteAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NlyteCustImportAction.class);

	// private List<NlyteUsers> nlyteUsers = new ArrayList<NlyteUsers>();
	private File fileNUpload;
	private String myFileFileName;
	private String myFileContentType;
	private String custHeaderChk;
	private String dataCheck;
	private String sheetsCheck;

	private String cmsId;

	public String getCmsId() {
		return cmsId;
	}

	public void setCmsId(String cmsId) {
		this.cmsId = cmsId;
	}

	public String getSheetsCheck() {
		return sheetsCheck;
	}

	public void setSheetsCheck(String sheetsCheck) {
		this.sheetsCheck = sheetsCheck;
	}

	public String getDataCheck() {
		return dataCheck;
	}

	public void setDataCheck(String dataCheck) {
		this.dataCheck = dataCheck;
	}

	public String getCustHeaderChk() {
		return custHeaderChk;
	}

	public void setCustHeaderChk(String custHeaderChk) {
		this.custHeaderChk = custHeaderChk;
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

	private File destFile;

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

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	private String fileNUploadContentType;
	private File myFile;
	private String filePath = null;
	protected Map<String, Object> session;
	private List<NlyteCustomerDataStg> nlyteCustDataStgLst = new ArrayList<NlyteCustomerDataStg>();
	private List<NlyteMasterDataTransStg> nlyteMastDataTransStg = new ArrayList<NlyteMasterDataTransStg>();

	public List<NlyteMasterDataTransStg> getNlyteMastDataTransStg() {
		return nlyteMastDataTransStg;
	}

	public void setNlyteMastDataTransStg(List<NlyteMasterDataTransStg> nlyteMastDataTransStg) {
		this.nlyteMastDataTransStg = nlyteMastDataTransStg;
	}

	public List<NlyteCustomerDataStg> getNlyteCustDataStgLst() {
		return nlyteCustDataStgLst;
	}

	public void setNlyteCustDataStgLst(List<NlyteCustomerDataStg> nlyteCustDataStgLst) {
		this.nlyteCustDataStgLst = nlyteCustDataStgLst;
	}

	@Override
	public String execute() throws Exception {
		// logger.info("execute() : NlyteCustImportAction");
		// try {
		// FileInputStream fileStream = new FileInputStream(fileUpload);
		// XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
		// File destFile = new File(destPath, fileName);
		// }
		/*
		 * catch(Exception e) { e.printStackTrace(); return "error"; }
		 */
		return "success";

	}

	public String nlyteCustView1() {
		try {
			logger.info("nlyteCustView1() : NlyteCustImportAction");
			this.nlyteCustDataStgLst = this.nlyteService.getCustomerDtStgImportList();
			setNlyteCustDataStgLst(nlyteCustDataStgLst);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}
	}

	public String display() {
		return "error";
	}

	public String nlyteCustImportExcel() {

		return "nlyteCustImportReq";
	}

	public String nlyteCustomerImportBulk() {
		logger.info("nlyteCustomerImportBulk() : NlyteCustImportAction");
		boolean flag = Boolean.FALSE;

		/* Copy file to a safe location */
		String destPath = "./";

		try {
			destFile = new File(destPath, myFileFileName);
			FileUtils.copyFile(myFile, destFile);

		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
		int cmsId = -1;
		try {
			int chkHeader = headerCheck(destFile);
			// System.out.println("chkHeader...."+chkHeader+"getSheetsCheck...."+getSheetsCheck()+"getDataCheck...."+getDataCheck());
			// if (chkHeader == 0 && getSheetsCheck() == null && getDataCheck() == null) {
			if (chkHeader == 0 && getDataCheck() == null) {
				NlyteCustomerDataStg nlyteCustDtStg = new NlyteCustomerDataStg();
				// this.user.getNlyteUserId();
				String userId = this.user.getLoginId();
				String displayName = this.user.getUserFirstNameTx().toString() + ","
						+ this.user.getUserLastNameTx().toString();
				nlyteCustDtStg.setDescriptionTx(myFileFileName);
				nlyteCustDtStg.setLstUpdtDtm(new Date());
				nlyteCustDtStg.setCreatedDt(new Date());
				// nlyteCustDtStg.setExcelFileBlb(SerializationUtils.serialize(nlyteCustDtStg));
				nlyteCustDtStg.setActiveYn(true);
				nlyteCustDtStg.setLoginId(userId);
				nlyteCustDtStg.setUserDisplayNameTx(displayName);
				nlyteCustDtStg.setStatus("N");
				nlyteCustDtStg.setProcessStatus("Y");
				nlyteCustDtStg.setUniqueId(UUID.randomUUID().toString());
				// stg.setExcelFileName(excelFileName);

				byte[] bytesArray = new byte[(int) myFile.length()];
				FileInputStream fis = new FileInputStream(myFile);
				fis.read(bytesArray);
				fis.close();
				nlyteCustDtStg.setExcelFileBlb(bytesArray);
				flag = this.nlyteService.nlyteCustomerImportBulk(nlyteCustDtStg);

				// NlyteCustomerDataStg l= new NlyteCustomerDataStg();
				NlyteCustomerDataStg l = new NlyteCustomerDataStg();
				l = this.nlyteService.getCustomerPrimaryDataByUUID(nlyteCustDtStg.getUniqueId());

				if (nlyteCustDtStg != null && null != nlyteCustDtStg.getCmsId()) {
					cmsId = nlyteCustDtStg.getCmsId();
					// getNlyteCustPrimaryKey(i);
					NlyteCustImportStgAction nlyteCustImptStgAct = new NlyteCustImportStgAction();
					String s = nlyteCustImptStgAct.excelRead(l.getCmsId(), nlyteService, destFile);
				}
				this.nlyteCustDataStgLst = this.nlyteService.getCustomerDtStgImportList();
				setNlyteCustDataStgLst(nlyteCustDataStgLst);
				if (flag) {

					return "success";
				} else {
					return "error";
				}

			} else {
				this.nlyteCustDataStgLst = this.nlyteService.getCustomerDtStgImportList();
				setNlyteCustDataStgLst(nlyteCustDataStgLst);
				setCustHeaderChk(String.valueOf(chkHeader));
				return "error";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}

	}

	public String nlyteCustomerImportList() {
		try {
			List<NlyteCustomerDataStg> nlyteCustDtStg = this.nlyteService.getCustomerDtStgImportList();
			setNlyteCustDataStgLst(nlyteCustDtStg);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String deleteCustData() {
		try {
			logger.info("nlyteCustomerImportBulk() : deleteCustData");
			System.out.println("cmsId===" + getCmsId());
			int id = Integer.parseInt(getCmsId());
			this.nlyteService.deleteCustRcrd(id);
			List<NlyteCustomerDataStg> nlyteCustDtStg = this.nlyteService.getCustomerDtStgImportList();
			setNlyteCustDataStgLst(nlyteCustDtStg);
			return "success";
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}
	}

	public int getNlyteCustPrimaryKey(int i) {
		return i;
	}

	public String nlyteCustomerProcessedList() {
		List<NlyteCustomerDataStg> nlyteCustDtStg = this.nlyteService.getCustomerDtStgProcessedList();
		setNlyteCustDataStgLst(nlyteCustDtStg);
		return "success";
	}

	public String nlyteCustomerProcessCompletedList() {
		logger.info("nlyteCustomerProcessCompletedList() : NlyteCustImportAction");
		List<NlyteCustomerDataStg> nlyteCustDtStg = this.nlyteService.getCustomerDtStgProcessCompletedList();
		List<NlyteCustomerDataStg> nlyteCustDtStg1 = new ArrayList<NlyteCustomerDataStg>();
		List<NlyteProcessedCountBean> procCount1 = this.nlyteService.getCustomerDtStgProcessedCount();

		for (NlyteCustomerDataStg stg : nlyteCustDtStg) {
			for (NlyteProcessedCountBean bean : procCount1) {
				NlyteCustomerDataStg stg1 = new NlyteCustomerDataStg();
				if (stg.getCmsId() == bean.getCustStgId()) {
					stg1 = stg;
					stg1.setMatchCount(bean.getMatchCount());
					stg1.setUnMatchCount(bean.getUnMatchCount());
					stg1.setMultiMatchedCount(bean.getMultiMatchedCount());
					nlyteCustDtStg1.add(stg1);
				}
			}
		}

		// System.out.println("nlyteCustDtStg1=="+nlyteCustDtStg1);

		setNlyteCustDataStgLst(nlyteCustDtStg1);

		return "success";
	}

	public String nlyteCustomerReviewList() {
		logger.info("nlyteCustomerReviewList() : NlyteCustImportAction");
		List<NlyteCustomerDataStg> nlyteCustDtStg = this.nlyteService.getCustomerDtStgReviewList();
		setNlyteCustDataStgLst(nlyteCustDtStg);
		return "success";
	}

	public int headerCheck(File destFile) {
		logger.info("headerCheck() : NlyteCustImportAction");
		int checkHeader = 0;
		FileInputStream fis;
		XSSFWorkbook workbook = null;
		try {
			fis = new FileInputStream(destFile);
			workbook = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println("No.of Sheets...."+workbook.getNumberOfSheets());
		if (workbook.getNumberOfSheets() > 1)
			setSheetsCheck("1");
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

			checkHeader = 1;
		}
		int nullCheck = 1;
		int rowVal = 1;
		for (int i = 1; i < rowNum; i++) {
			XSSFRow row = sheet.getRow(i);
			if (row != null) {
				// System.out.println("********** model value is " +
				// cellToString(row.getCell(model)));

				if (cellToString(row.getCell(model)).equals("0.0")) {
					nullCheck++;
				}
				rowVal++;
			}
		}

		if (rowVal == nullCheck) {

			setDataCheck("1");
		}

		return checkHeader;

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

}
