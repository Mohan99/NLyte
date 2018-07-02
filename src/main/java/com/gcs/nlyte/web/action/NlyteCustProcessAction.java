package com.gcs.nlyte.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.tiles.request.servlet.ServletApplicationContext;

import com.gcs.nlyte.web.bean.NltyeCustDataProcessBean;
import com.gcs.nlyte.web.bean.NltyeCustDataSelectProcessBean;
import com.gcs.nlyte.web.persistance.dao.entity.NltyeCustomerDataProcess;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteCustomerDataTransStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataStg;
import com.gcs.nlyte.web.persistance.dao.entity.NlyteMasterDataTransStg;
import com.gcs.nlyte.web.persistance.dao.impl.NlyteCustomerDataStgDao;

public class NlyteCustProcessAction extends NLyteAction {
	private static final long serialVersionUID = 1L;
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NlyteCustProcessAction.class);

	private List<NlyteCustomerDataTransStg> nlyteCustDataTransStg = new ArrayList<NlyteCustomerDataTransStg>();
	NlyteMasterDataStg nlyteMasterDataStg = new NlyteMasterDataStg();
	private List<NlyteCustomerDataTransStg> nlyteCustDtTransStgLst = new ArrayList<NlyteCustomerDataTransStg>();
	private List<NlyteCustomerDataStg> nlyteCustDataStgLst = new ArrayList<NlyteCustomerDataStg>();
	private List<NltyeCustDataProcessBean> listBean;
	private String processNctId;
	private String Processes;
	private List<NltyeCustDataProcessBean> selectBean;

	public List<NltyeCustDataProcessBean> getSelectBean() {
		return selectBean;
	}

	public void setSelectBean(List<NltyeCustDataProcessBean> selectBean) {
		this.selectBean = selectBean;
	}

	public String getProcesses() {
		return Processes;
	}

	public void setProcesses(String processes) {
		Processes = processes;
	}

	public String getProcessNctId() {
		return processNctId;
	}

	public void setProcessNctId(String processNctId) {
		this.processNctId = processNctId;
	}

	public List<NltyeCustDataProcessBean> getListBean() {
		return listBean;
	}

	public void setListBean(List<NltyeCustDataProcessBean> listBean) {
		this.listBean = listBean;
	}

	public List<NlyteCustomerDataStg> getNlyteCustDataStgLst() {
		return nlyteCustDataStgLst;
	}

	public void setNlyteCustDataStgLst(List<NlyteCustomerDataStg> nlyteCustDataStgLst) {
		this.nlyteCustDataStgLst = nlyteCustDataStgLst;
	}

	public List<NlyteCustomerDataTransStg> getNlyteCustDtTransStgLst() {
		return nlyteCustDtTransStgLst;
	}

	public void setNlyteCustDtTransStgLst(List<NlyteCustomerDataTransStg> nlyteCustDtTransStgLst) {
		this.nlyteCustDtTransStgLst = nlyteCustDtTransStgLst;
	}

	public NlyteMasterDataStg getNlyteMasterDataStg() {
		return nlyteMasterDataStg;
	}

	public void setNlyteMasterDataStg(NlyteMasterDataStg nlyteMasterDataStg) {
		this.nlyteMasterDataStg = nlyteMasterDataStg;
	}

	public List<NlyteCustomerDataTransStg> getNlyteCustDataTransStg() {
		return nlyteCustDataTransStg;
	}

	public void setNlyteCustDataTransStg(List<NlyteCustomerDataTransStg> nlyteCustDataTransStg) {
		this.nlyteCustDataTransStg = nlyteCustDataTransStg;
	}

	private String cmsId;
	/*
	 * @Autowired private INlyteService nlyteService;
	 */

	public String getCmsId() {
		return cmsId;
	}

	public void setCmsId(String cmsId) {
		this.cmsId = cmsId;
	}

	/*
	 * public static String removeNPChars(String str) { while (str.indexOf("  ") >=
	 * 0) { str = str.replaceAll("  ", " "); } str = str.replaceAll("^\\s+", "");
	 * str = str.replaceAll("\\s+$", ""); str = str.replaceAll("\\p{C}", ""); str =
	 * str.replaceAll("[^\\x00-\\x7F]", ""); str =
	 * str.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", ""); str=str.toUpperCase(); return
	 * str; }
	 */

	public String getCustProcesData() {
		logger.info("getCustProcesData() : NlyteCustProcessAction");
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
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}
	}

	public String getCustProcesDataExport() {
		logger.info("getCustProcesDataExport() : NlyteCustProcessAction");
		boolean flag = Boolean.FALSE;
		try {
			List<NltyeCustDataProcessBean> nlyteCustDtTransUpdated = this.nlyteService
					.getCustomerDataProcessedList(getCmsId());
			List<NltyeCustDataProcessBean> nlyteCustDtTransUpdatedAll = this.nlyteService
					.findCustProcessedAllList(getCmsId());

			if (nlyteCustDtTransUpdated != null && nlyteCustDtTransUpdated.size() > 0) {
				doExport(nlyteCustDtTransUpdated);

				return "success";
			} else {
				return "error";
			}
			// setListBean(nlyteCustDtTransUpdated);
			// setSelectBean(nlyteCustDtTransUpdatedAll);

		} catch (Exception ex) {
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}
	}

	public void doExport(List<NltyeCustDataProcessBean> dataList) throws Exception {
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
			for (NltyeCustDataProcessBean details : dataList) {
				NlyteCustomerDataTransStg nlyteCustomerDataTransStg = details.getnCustDataTransStg();
				HSSFRow row = sheet.createRow(rowNo);
				row.createCell((short) 0).setCellValue(rowNo);
				row.createCell((short) 1).setCellValue(nlyteCustomerDataTransStg.getModelTx());
				row.createCell((short) 2).setCellValue(nlyteCustomerDataTransStg.getNmsValue());
				row.createCell((short) 3).setCellValue(nlyteCustomerDataTransStg.getManufacturerTx());
				row.createCell((short) 4).setCellValue(nlyteCustomerDataTransStg.getMaterialTypTx());
				row.createCell((short) 5).setCellValue(nlyteCustomerDataTransStg.getMaterialSubTypTx());
				row.createCell((short) 6).setCellValue(nlyteCustomerDataTransStg.getPowerConsmptNm());
				row.createCell((short) 7).setCellValue(nlyteCustomerDataTransStg.getWidthNm());
				row.createCell((short) 8).setCellValue(nlyteCustomerDataTransStg.getDepthNm());
				row.createCell((short) 9).setCellValue(nlyteCustomerDataTransStg.getHeightNm());
				row.createCell((short) 10).setCellValue(nlyteCustomerDataTransStg.getWeightNm());
				row.createCell((short) 11).setCellValue(nlyteCustomerDataTransStg.getCopperPortsTx());
				row.createCell((short) 12).setCellValue(nlyteCustomerDataTransStg.getFiberPortsTx());
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

			String file = "./ProcesseMatched_Data.xls";
			try {
				FileOutputStream fos = new FileOutputStream(file);
				workBook.write(fos);
				fos.flush();
				HttpServletResponse response = ServletActionContext.getResponse();
				downloadFile(file, response);
			} catch (FileNotFoundException e) {
				StringWriter errors = new StringWriter();
				logger.error("Exception Message: " + errors.toString());
			} catch (IOException e) {
				StringWriter errors = new StringWriter();
				logger.error("Exception Message: " + errors.toString());
			} catch (Exception e) {
				StringWriter errors = new StringWriter();
				logger.error("Exception Message: " + errors.toString());
			}
		}
	}

	private void downloadFile(final String fileName, HttpServletResponse response) {
		try {
			final File f = new File(fileName);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "inline; filename=" + fileName);
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "no-store");
			response.addHeader("Cache-Control", "max-age=0");
			FileInputStream fin = null;
			try {
				fin = new FileInputStream(f);
			} catch (final FileNotFoundException e) {
				e.printStackTrace();
			}
			final int size = 1024;
			try {
				response.setContentLength(fin.available());
				final byte[] buffer = new byte[size];
				ServletOutputStream os = null;

				os = response.getOutputStream();
				int length = 0;
				while ((length = fin.read(buffer)) != -1) {
					os.write(buffer, 0, length);
				}
				fin.close();
				os.flush();
				os.close();
			} catch (final IOException e) {
				StringWriter errors = new StringWriter();
				logger.error("Exception Message: " + errors.toString());
			}
		} catch (final Exception ex) {
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
		}
	}

	public String nlyteProcessSeleted() {
		logger.info("nlyteProcessSeleted() : NlyteCustProcessAction");
		String keyvalues[] = getProcesses().split(",");
		String cmsIDs[] = getCmsId().split(",");
		String nctIds[] = new String[keyvalues.length / 2];
		String values[] = new String[keyvalues.length / 2];
		int cmsId = Integer.parseInt(cmsIDs[0]);
		int d = 0;
		int e = 0;
		for (int i = 0; i < keyvalues.length; i++) {

			if ((i % 2 == 0)) {
				values[d] = keyvalues[i];
				//
				d++;
			} else {
				nctIds[e] = keyvalues[i];
				// b[i]="";
				e++;
			}

		}

		for (int j = 0; j < nctIds.length; j++) {
			boolean updateStatus = false;
			if (!values[j].equals("")) {

				updateStatus = this.nlyteService.updateCustProcessedByIds(Integer.parseInt(nctIds[j]),
						Integer.parseInt(values[j]), cmsId);
			}

		}

		List<NlyteCustomerDataStg> nlyteCustDtStgList = this.nlyteService.getCustomerDtStgImportList();
		setNlyteCustDataStgLst(nlyteCustDtStgList);

		return "success";
	}

	// The below method is the ajax method, is not used for now.
	public String getCustProcessedDataById() {
		logger.info("getCustProcessedDataById() : NlyteCustProcessAction");
		List<NltyeCustDataSelectProcessBean> processList = this.nlyteService
				.getCustomerDataProcessedListById(getProcessNctId());
		Iterator<NltyeCustDataSelectProcessBean> itr = processList.iterator();
		StringBuffer sbrObj = new StringBuffer();
		while (itr.hasNext()) {
			NltyeCustDataSelectProcessBean nCustDataProcess = itr.next();
			sbrObj.append("<option value='").append(nCustDataProcess.getnMasterDataTrans().getNmtId()).append("'>")
					.append(nCustDataProcess.getnMasterDataTrans().getModelTx()).append("</option>");
		}
		if (sbrObj != null) {
			Processes = sbrObj.toString();
		}
		return "success";
	}

	@Override
	public String execute() {
		logger.info("execute() : NlyteCustProcessAction");
		boolean flag = Boolean.FALSE;
		int retValOne = 0;
		int retVal = 0;
		try {
			List<NltyeCustDataProcessBean> nlyteCustDtTransBean = this.nlyteService
					.getCustomerDataTransStgListEqual(getCmsId());

			List<NlyteCustomerDataTransStg> nlyteCustDtTrans = null;
			List<NlyteMasterDataTransStg> nlyteMasterDataTransStg = nlyteService.getnlyteMasterList();

			if (nlyteCustDtTransBean.size() > 0)
				retValOne = compareUnmatchedLists(nlyteCustDtTransBean, getCmsId());

			nlyteCustDtTrans = this.nlyteService.getCustomerDataTransUnmatchedList(getCmsId());
			retVal = compareLists(nlyteMasterDataTransStg, nlyteCustDtTrans, getCmsId());
			// after first check gettig the new list to below
			nlyteCustDtTrans = this.nlyteService.getCustomerDataTransUnmatchedList(getCmsId());
			retVal = compareListsOne(nlyteMasterDataTransStg, nlyteCustDtTrans, getCmsId());

			List<NlyteCustomerDataTransStg> nlyteCustDtTransUpdated = this.nlyteService
					.getCustomerDataTransStgList(getCmsId());
			setNlyteCustDataTransStg(nlyteCustDtTransUpdated);
			int cmsIdIntval = Integer.parseInt(getCmsId());
			boolean retval = this.nlyteService.updateCustStgData(Integer.parseInt(getCmsId()));

			List<NlyteCustomerDataStg> nlyteCustDtStg = this.nlyteService.getCustomerDtStgImportList();
			NlyteCustomerDataStg custStgData = new NlyteCustomerDataStg();
			for (NlyteCustomerDataStg prevListdata : nlyteCustDtStg) {
				if (prevListdata.getCmsId() == cmsIdIntval) {
					custStgData = prevListdata;
					custStgData.setStatus("Y");
					if (retVal > 0 || retValOne > 0)
						custStgData.setProcessStatus("N");
					boolean ret = this.nlyteService.nlyteCustStgUpdate(custStgData);
				}
			}

			List<NlyteCustomerDataStg> nlyteCustDtStgList = this.nlyteService.getCustomerDtStgImportList();
			setNlyteCustDataStgLst(nlyteCustDtStgList);

			return "success";
		} catch (Exception ex) {
			ex.printStackTrace();
			StringWriter errors = new StringWriter();
			logger.error("Exception Message: " + errors.toString());
			return "error";
		}
	}

	public String getProcessData() {
		this.nlyteCustDataStgLst = this.nlyteService.getCustomerDtStgImportList();
		setNlyteCustDataStgLst(nlyteCustDataStgLst);
		return "success";

	}

	public int compareUnmatchedLists(List<NltyeCustDataProcessBean> beanList, String cmsId) {
		logger.info("compareUnmatchedLists() : NlyteCustProcessAction");
		int foundVal = 0;
		for (NltyeCustDataProcessBean modelListdata : beanList) {

			List<NlyteCustomerDataTransStg> custdatatranstgList = new ArrayList<NlyteCustomerDataTransStg>();
			List<NltyeCustomerDataProcess> nltyeCustDataProcessList = new ArrayList<NltyeCustomerDataProcess>();

			NlyteCustomerDataTransStg curModelListdata = new NlyteCustomerDataTransStg();
			NltyeCustomerDataProcess processData = new NltyeCustomerDataProcess();

			modelListdata.getnCustDataTransStg().setMatchPercentage("100%");
			modelListdata.getnCustDataTransStg().setNmsId(modelListdata.getnMasterDataTrans().getNmtId());
			modelListdata.getnCustDataTransStg().setNmsValue(modelListdata.getnMasterDataTrans().getModelTx());
			curModelListdata = modelListdata.getnCustDataTransStg();

			custdatatranstgList.add(curModelListdata);

			processData.setCreatedDt(new Date());
			processData.setUpdatedDt(new Date());
			processData.setStatus(0);
			processData.setPerentage("100%");
			processData.setNlyteCustomerDataTransStg(modelListdata.getnCustDataTransStg());
			processData.setNlyteMasterDataTransStg(modelListdata.getnMasterDataTrans());
			nltyeCustDataProcessList.add(processData);

			if (custdatatranstgList.size() == 1) {
				for (NlyteCustomerDataTransStg prevListdata : custdatatranstgList) {
					boolean flag = this.nlyteService.nlyteCustomerUpdate(prevListdata);
				}
			}

		}
		return foundVal;
	}

	public int compareLists(List<NlyteMasterDataTransStg> prevList, List<NlyteCustomerDataTransStg> modelList,
			String cmsId) {
		logger.info("compareLists() : NlyteCustProcessAction");
		int foundVal = 0;
		for (NlyteCustomerDataTransStg modelListdata : modelList) {
			List<NlyteCustomerDataTransStg> custdatatranstgList = new ArrayList<NlyteCustomerDataTransStg>();
			List<NltyeCustomerDataProcess> nltyeCustDataProcessList = new ArrayList<NltyeCustomerDataProcess>();

			String custManuName = modelListdata.getManufacturerTx();
			custManuName.toUpperCase();
			
			if (custManuName.equals("HEWLETT-PACKARD") || custManuName.equals("HEWLETT PACKARD")) {
				custManuName = "HP";
			}
			
			if (custManuName.equalsIgnoreCase("D-LINK")
					|| custManuName.equalsIgnoreCase("D LINK")) {
				custManuName = "D-LINK";
				
			}

			String newCustComp = (new StringBuilder()).append(custManuName).append(" ")
					.append(modelListdata.getModelTx()).toString(); // newCustComp =
			// removeNPChars(newCustComp);
			newCustComp = newCustComp.toUpperCase();

			String custModelStr = modelListdata.getModelTx();

			// custModelStr = removeNPChars(custModelStr); custModelStr =
			custModelStr.toUpperCase();
			NlyteCustomerDataTransStg curModelListdata = new NlyteCustomerDataTransStg();

			for (NlyteMasterDataTransStg prevListdata : prevList) {

				String mastMaterialStr = prevListdata.getMaterialNmTx();
				String mastModelStr = prevListdata.getModelTx();

				/*
				 * mastMaterialStr = removeNPChars(mastMaterialStr); mastModelStr =
				 * removeNPChars(mastModelStr);
				 */

				mastMaterialStr = mastMaterialStr.toUpperCase();
				mastModelStr = mastModelStr.toUpperCase();
				String mastManuName = prevListdata.getManufacturerTx();
				

				mastManuName.toUpperCase();

				if (mastManuName.equalsIgnoreCase("HEWLETT-PACKARD")
						|| mastManuName.equalsIgnoreCase("HEWLETT PACKARD")) {
					mastManuName = "HP";
				}
				
				if (mastManuName.equalsIgnoreCase("D-LINK")
						|| mastManuName.equalsIgnoreCase("D LINK")) {
					mastManuName = "D-LINK";
					
				}

				// if (mastMaterialStr.contains(custModelStr) ||
				// (mastModelStr.contains(custModelStr)) ||
				if ((mastModelStr.contains(newCustComp)) || (mastMaterialStr.contains(newCustComp))) {

					modelListdata.setMatchPercentage("70%");
					modelListdata.setNmsId(prevListdata.getNmtId());
					modelListdata.setNmsValue(prevListdata.getModelTx());
					custdatatranstgList.add(modelListdata);
					if (mastManuName.equalsIgnoreCase(custManuName)) {
						NltyeCustomerDataProcess processData = new NltyeCustomerDataProcess();

						processData.setCreatedDt(new Date());
						processData.setUpdatedDt(new Date());
						processData.setStatus(0);
						processData.setPerentage("50%");
						processData.setNlyteCustomerDataTransStg(modelListdata);
						processData.setNlyteMasterDataTransStg(prevListdata);
						nltyeCustDataProcessList.add(processData);
					}
				}

			}

			if (nltyeCustDataProcessList.size() > 1) {
				foundVal++;
				for (NltyeCustomerDataProcess prevListdata : nltyeCustDataProcessList) {
					boolean flagone = this.nlyteService.nlyteCustProcessIns(prevListdata);
				}
			}
			if (custdatatranstgList.size() == 1) {
				for (NlyteCustomerDataTransStg prevListdata : custdatatranstgList) {
					boolean flag = this.nlyteService.nlyteCustomerUpdate(prevListdata);
				}
			}

		}
		return foundVal;
	}

	public int compareListsOne(List<NlyteMasterDataTransStg> prevList, List<NlyteCustomerDataTransStg> modelList,
			String cmsId) {
		logger.info("compareLists() : NlyteCustProcessAction");
		int foundVal = 0;
		for (NlyteCustomerDataTransStg modelListdata : modelList) {
			List<NlyteCustomerDataTransStg> custdatatranstgList = new ArrayList<NlyteCustomerDataTransStg>();
			List<NltyeCustomerDataProcess> nltyeCustDataProcessList = new ArrayList<NltyeCustomerDataProcess>();

			String custManuName = modelListdata.getManufacturerTx();
			custManuName.toUpperCase();
						if (custManuName.equals("HEWLETT-PACKARD") || custManuName.equals("HEWLETT PACKARD")) {
				custManuName = "HP";
				
			}
			
			if (custManuName.equalsIgnoreCase("D-LINK")
					|| custManuName.equalsIgnoreCase("D LINK")) {
				custManuName = "D-LINK";
				
			}

			String newCustComp = (new StringBuilder()).append(custManuName).append(" ")
					.append(modelListdata.getModelTx()).toString(); // newCustComp =
			newCustComp = newCustComp.toUpperCase();

			String custModelStr = modelListdata.getModelTx();

			// custModelStr = removeNPChars(custModelStr); custModelStr =
			custModelStr.toUpperCase();
			NlyteCustomerDataTransStg curModelListdata = new NlyteCustomerDataTransStg();

			for (NlyteMasterDataTransStg prevListdata : prevList) {

				String mastMaterialStr = prevListdata.getMaterialNmTx();
				String mastModelStr = prevListdata.getModelTx();
				String mastManuName = prevListdata.getManufacturerTx();
				mastManuName.toUpperCase();
				if (mastManuName.equalsIgnoreCase("HEWLETT-PACKARD")
						|| mastManuName.equalsIgnoreCase("HEWLETT PACKARD")) {
					mastManuName = "HP";
					
				}
				
				if (mastManuName.equalsIgnoreCase("D-LINK")
						|| mastManuName.equalsIgnoreCase("D LINK")) {
					mastManuName = "D-LINK";
					
				}


				/*
				 * mastMaterialStr = removeNPChars(mastMaterialStr); mastModelStr =
				 * removeNPChars(mastModelStr);
				 */

				mastMaterialStr = mastMaterialStr.toUpperCase();
				mastModelStr = mastModelStr.toUpperCase();

				if (mastMaterialStr.contains(custModelStr) || (mastModelStr.contains(custModelStr))) {

					if (mastManuName.equalsIgnoreCase(custManuName)) {
						modelListdata.setMatchPercentage("70%");
						modelListdata.setNmsId(prevListdata.getNmtId());
						modelListdata.setNmsValue(prevListdata.getModelTx());
						custdatatranstgList.add(modelListdata);
						// if(mastManuName.equalsIgnoreCase(custManuName)) {
						NltyeCustomerDataProcess processData = new NltyeCustomerDataProcess();
						processData.setCreatedDt(new Date());
						processData.setUpdatedDt(new Date());
						processData.setStatus(0);
						processData.setPerentage("50%");
						processData.setNlyteCustomerDataTransStg(modelListdata);
						processData.setNlyteMasterDataTransStg(prevListdata);
						nltyeCustDataProcessList.add(processData);
					}
				}

			}

			if (nltyeCustDataProcessList.size() > 1) {
				foundVal++;
				for (NltyeCustomerDataProcess prevListdata : nltyeCustDataProcessList) {
					boolean flagone = this.nlyteService.nlyteCustProcessIns(prevListdata);
				}
			}
			if (custdatatranstgList.size() == 1) {
				for (NlyteCustomerDataTransStg prevListdata : custdatatranstgList) {
					boolean flag = this.nlyteService.nlyteCustomerUpdate(prevListdata);
				}
			}

		}
		return foundVal;
	}

	public String custToBeProcessList() {
		List<NlyteCustomerDataStg> nlyteCustDtStg = this.nlyteService.getCustToBeProcessList();
		setNlyteCustDataStgLst(nlyteCustDtStg);
		return "success";
	}

	public String nlyteEditUser() {

		return "nlyteUserEdit";
	}
}
