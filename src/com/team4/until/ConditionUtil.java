package com.team4.until;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class ConditionUtil {

	public static <T> String getCondition(String[] box, T form)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String condition = "";
		if (box != null) {
			for (int i = 0; i < box.length; i++) {
				// 简拼
				if (box[i].equals("pinyin")) {
					condition += "简拼为：" + BeanUtils.getProperty(form, "pinyin")
							+ " ";
				}
				// 销售员
				else if (box[i].equals("saler")) {
					condition += "销售员为：" + BeanUtils.getProperty(form, "saler")
							+ " ";
				}
				// 消费合计
				else if (box[i].equals("total_cost")) {
					condition += "消费合计为："
							+ BeanUtils.getProperty(form, "l_total_cost") + "-"
							+ BeanUtils.getProperty(form, "r_total_cost") + " ";
				}
				// 日期
				else if (box[i].equals("date")) {
					condition += "日期为：" + BeanUtils.getProperty(form, "l_date")
							+ "-" + BeanUtils.getProperty(form, "r_date") + " ";
				}
				// 电话
				else if (box[i].equals("tel")) {
					condition += "电话为：" + BeanUtils.getProperty(form, "tel")
							+ " ";
				}
				// 合约名称
				else if (box[i].equals("contractname")) {
					condition += "合约名称为：" + BeanUtils.getProperty(form, "contractname") + " ";
				}
				// 备注
				else if (box[i].equals("remark")) {
					condition += "备注为：" + BeanUtils.getProperty(form, "remark") + " ";
				}
				// vip否？
				else if (box[i].equals("khtype")) {
					condition += "VIP ";
				}
				// 合约代表
				else if (box[i].equals("codelegate")) {
					condition += "合约代表为：" + BeanUtils.getProperty(form, "codelegate") + " ";
				}
				// 合约类型
				else if (box[i].equals("cotype")) {
					condition += "合约类型为：" + BeanUtils.getProperty(form, "cotype") + " ";
				}
				// 签约日期
				else if (box[i].equals("bedate")) {
					condition += "签约日期为：" + BeanUtils.getProperty(form, "l_date")
							+ "-" + BeanUtils.getProperty(form, "r_date") + " ";
				}
			}
		}
		return condition;
	}
}
