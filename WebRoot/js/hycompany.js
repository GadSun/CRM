//文本框能否输入-----------------------------------------------------------------------------
function oper() {
	var c1 = document.getElementById("chkcodelegate");
	if (c1.checked) {
		document.getElementById("txcodelegate").disabled = false;
	} else {
		document.getElementById("txcodelegate").disabled = true;
		document.getElementById("txcodelegate").value = "";
	}
}
function oper1() {
	var c2 = document.getElementById("chkpinyin");
	if (c2.checked) {
		document.getElementById("txpinyin").disabled = false;
	} else {
		document.getElementById("txpinyin").disabled = true;
		document.getElementById("txpinyin").value = "";
	}
}
function oper2() {
	var c3 = document.getElementById("chkbedate");
	if (c3.checked) {
		document.getElementById("txlbedate").disabled = false;
		document.getElementById("txrbedate").disabled = false;
	} else {
		document.getElementById("txlbedate").disabled = true;
		document.getElementById("txrbedate").disabled = true;
		document.getElementById("txlbedate").value = "";
		document.getElementById("txrbedate").value = "";
	}
}
function oper3() {
	var c4 = document.getElementById("chktotal_cost");
	if (c4.checked) {
		document.getElementById("txltotal_cost").disabled = false;
		document.getElementById("txrtotal_cost").disabled = false;
	} else {
		document.getElementById("txltotal_cost").disabled = true;
		document.getElementById("txrtotal_cost").disabled = true;
		document.getElementById("txltotal_cost").value = "";
		document.getElementById("txrtotal_cost").value = "";
	}
}
function oper4() {
	var c5 = document.getElementById("chktel");
	if (c5.checked) {
		document.getElementById("txtel").disabled = false;
	} else {
		document.getElementById("txtel").disabled = true;
		document.getElementById("txtel").value = "";
	}
}
function oper5() {
	var c6 = document.getElementById("chksaler");
	if (c6.checked) {
		document.getElementById("slcsaler").disabled = false;
	} else {
		document.getElementById("slcsaler").disabled = true;
	}
}
function oper6() {
	var c7 = document.getElementById("chkcotype");
	if (c7.checked) {
		document.getElementById("slccotype").disabled = false;
	} else {
		document.getElementById("slccotype").disabled = true;
	}
}
// 按查询按钮-----------------------------------------------------------------------------
function select() {
	// 清除复选框对号-----------------------------------------------------------------------------
	var a1 = document.getElementById("txcodelegate");
	var a2 = document.getElementById("txpinyin");
	var a3 = document.getElementById("slcsaler");
	var a4 = document.getElementById("slccotype");
	var a6 = document.getElementById("txlbedate");
	var a7 = document.getElementById("txrbedate");
	var a8 = document.getElementById("txltotal_cost");
	var a9 = document.getElementById("txrtotal_cost");
	var a10 = document.getElementById("txtel");
	var c1 = document.getElementById("chkcodelegate");
	var c2 = document.getElementById("chkpinyin");
	var c3 = document.getElementById("chksaler");
	var c4 = document.getElementById("chkcotype");
	var c5 = document.getElementById("chkbedate");
	var c6 = document.getElementById("chktotal_cost");
	var c7 = document.getElementById("chktel");
	var c8 = document.getElementById("chkbackcost");
	var c9 = document.getElementById("chkremark");
	if (a1.value == "") {
		c1.checked = false;
		document.getElementById("txcodelegate").disabled = true;
	}
	if (a2.value == "") {
		c2.checked = false;
		document.getElementById("txpinyin").disabled = true;
	}
	if (a6.value == "" && a7.value == "") {
		c5.checked = false;
		document.getElementById("txlbedate").disabled = true;
		document.getElementById("txrbedate").disabled = true;
	}
	if (a8.value == "" && a9.value == "") {
		c6.checked = false;
		document.getElementById("txltotal_cost").disabled = true;
		document.getElementById("txrtotal_cost").disabled = true;
	}
	if (a10.value == "") {
		c7.checked = false;
		document.getElementById("txtel").disabled = true;
	}
	// 进行页面跳转-----------------------------------------------------------------------------
	form1.action = document.getElementById("path").value
			+ "/servlet/HYGSServlet";
	form1.submit();
}
// 按清除按钮-----------------------------------------------------------------------------
function pageClear() {
	form1.action = document.getElementById("path").value
			+ "/servlet/HYGSUIServlet";
	form1.submit();
}
// 点击导出Excel表按钮-----------------------------------------------------------------------------
function getExcel() {
	console.log("1");
	var url = document.getElementById("path").value
			+ "/servlet/HYGSExcelServlet";
	$.ajax({
		type : "POST",
		async : true,
		url : url,
		Error : function(msg, status) {
		},
		success : function(msg) {
			// 请求成功,具体业务逻辑操作....
			if (msg["message"] != null) {
				alert("导出失败:1.请先点击查询按钮\n2.可能另一个程序正在使用此文件，进程无法访问");
			}
		}
	});
}
function getPrint(){
	form1.action = document.getElementById("path").value
	+ "/servlet/HYGSPrintServlet";
}
// 分页管理---------------------------------------------------------------------------------
window.onload = function() {
	page = new Page(20, 'table1', 'group_one');
};
function Page(iAbsolute, sTableId, sTBodyId) {
	this.absolute = iAbsolute; // 每页最大记录数
	this.tableId = sTableId;
	this.tBodyId = sTBodyId;
	this.rowCount = 0;// 记录数
	this.pageCount = 0;// 页数
	this.pageIndex = 0;// 页索引
	this.__oTable__ = null;// 表格引用
	this.__oTBody__ = null;// 要分页内容
	this.__dataRows__ = 0;// 记录行引用
	this.__oldTBody__ = null;
	this.__init__(); // 初始化;
};
/* 初始化 */
Page.prototype.__init__ = function() {
	this.__oTable__ = document.getElementById(this.tableId);// 获取table引用
	this.__oTBody__ = this.__oTable__.tBodies[this.tBodyId];// 获取tBody引用
	this.__dataRows__ = this.__oTBody__.rows;
	this.rowCount = this.__dataRows__.length;
	try {
		this.absolute = (this.absolute <= 0) || (this.absolute > this.rowCount) ? this.rowCount
				: this.absolute;
		this.pageCount = parseInt(this.rowCount % this.absolute == 0 ? this.rowCount
				/ this.absolute
				: this.rowCount / this.absolute + 1);
	} catch (exception) {
	}
	this.__updateTableRows__();
};
/*
 * 下一页
 */
Page.prototype.nextPage = function() {
	if (this.pageIndex + 1 < this.pageCount) {
		this.pageIndex += 1;
		this.__updateTableRows__();
	}
};
/*
 * 上一页
 */
Page.prototype.prePage = function() {
	if (this.pageIndex >= 1) {
		this.pageIndex -= 1;
		this.__updateTableRows__();
	}
};
/*
 * 首页
 */
Page.prototype.firstPage = function() {
	if (this.pageIndex != 0) {
		this.pageIndex = 0;
		this.__updateTableRows__();
	}
};
/*
 * 尾页
 */
Page.prototype.lastPage = function() {
	if (this.pageIndex + 1 != this.pageCount) {
		this.pageIndex = this.pageCount - 1;
		this.__updateTableRows__();
	}
};
/*
 * 页定位方法
 */
Page.prototype.aimPage = function(iPageIndex) {
	if (iPageIndex > this.pageCount - 1) {
		this.pageIndex = this.pageCount - 1;
	} else if (iPageIndex < 0) {
		this.pageIndex = 0;
	} else {
		this.pageIndex = iPageIndex;
	}
	this.__updateTableRows__();
};
/*
 * 执行分页时，更新显示表格内容
 */
Page.prototype.__updateTableRows__ = function() {
	var iCurrentRowCount = this.absolute * this.pageIndex;
	var iMoreRow = this.absolute + iCurrentRowCount > this.rowCount ? this.absolute
			+ iCurrentRowCount - this.rowCount
			: 0;
	var tempRows = this.__cloneRows__();
	// alert(tempRows === this.dataRows);
	// alert(this.dataRows.length);
	var removedTBody = this.__oTable__.removeChild(this.__oTBody__);
	var newTBody = document.createElement("TBODY");
	newTBody.setAttribute("id", this.tBodyId);

	for ( var i = iCurrentRowCount; i < this.absolute + iCurrentRowCount
			- iMoreRow; i++) {
		newTBody.appendChild(tempRows[i]);
	}
	this.__oTable__.appendChild(newTBody);
	/*
	 * this.dataRows为this.oTBody的一个引用， 移除this.oTBody那么this.dataRows引用将销失,
	 * code:this.dataRows = tempRows;恢复原始操作行集合.
	 */
	this.__dataRows__ = tempRows;
	this.__oTBody__ = newTBody;
	// alert(this.dataRows.length);
	// alert(this.absolute+iCurrentRowCount);
	// alert("tempRows:"+tempRows.length);

};
/*
 * 克隆原始操作行集合
 */
Page.prototype.__cloneRows__ = function() {
	var tempRows = [];
	for ( var i = 0; i < this.__dataRows__.length; i++) {
		tempRows[i] = this.__dataRows__[i].cloneNode(1);
	}
	return tempRows;
}
// 弹页面--------------------------------------------------------------------------------------------
function point(str) {

	var y1 = document.getElementById("mb");
	var y2 = document.getElementById("tbhycard");
	y1.style.display = "block";
	y2.style.display = "block";
	var url = document.getElementById("path").value
			+ "/servlet/HYGS_HYKPServlet?idFromPage=" + str;
	$.ajax({
		type : "POST",
		async : true,
		url : url,
		Error : function(msg, status) {
			console.log("!");
		},
		success : function(msg) {
			// 请求成功,具体业务逻辑操作....

			var dom = $("#tbhycard").find("tbody");
			dom.append('<tr>' + '<td align="left" class="card" width="30%">合约编号'
					+ msg["Id"] + '</td>'
					+ '<td align="left" class="card" width="30%">合约名称' + msg["Coname"]
					+ '</td>' + '<td align="left" class="card" width="30%">证件类型'
					+ msg["Certificatetype"] + '</td>' + '</tr>' + '<tr>'
					+ '<td align="left" class="card">联系电话' + msg["Tel"]
					+ '</td>' + '<td align="left" class="card">销售员'
					+ msg["Saler"] + '</td>'
					+ '<td align="left" class="card">证件编号'
					+ msg["Certificatenumber"] + '</td>' + '</tr>' + '<tr>'
					+ '<td align="left" class="card">签约日期' + msg["Bedate"]
					+ '</td>' + '<td align="left" class="card">结束日期'
					+ msg["Stdate"] + '</td>'
					+ '<td align="left" class="card">折扣比例' + msg["Stdiscount"]
					+ '</td>' + '</tr>' + '<tr>'
					+ '<td align="left" class="card" colspan="3" >公司名称' + msg["Resname"]
					+ '</td>' + '</tr>' + '<tr>'
					+ '<td align="left" class="card" colspan="3" >公司地址' + msg["Address"]
					+ '</td>' + '</tr>');
		}

	});
}
function point1(str) {
	var y1 = document.getElementById("mb");
	var y3 = document.getElementById("tbhycost");
	y1.style.display = "block";
	y3.style.display = "block";
	var url = document.getElementById("path").value
			+ "/servlet/HYGS_HYXYJServlet?idFromPage=" + str;
	$.ajax({
		type : "POST",
		async : true,
		url : url,
		Error : function(msg, status) {
		},
		success : function(msg) {
			// 请求成功,具体业务逻辑操作....
			console.log(msg);
			var dom = $("#tbhycost").find("tbody");
			for ( var i = 0; i < msg["size"]; i++) {
				console.log(msg["Id" + 0]);
				dom.append('<tr>' + '<td align="left" class="card">'
						+ msg["Id" + i] + '</td>'
						+ '<td align="left" class="card">' + msg["Setype" + i]
						+ '</td>' + '<td align="left" class="card">'
						+ msg["Fitype" + i] + '</td>'
						+ '<td align="left" class="card">'
						+ msg["Stdiscount" + i] + '</td>'
						+ '<td align="left" class="card">'
						+ msg["Prdiscount" + i] + '</td>'
						+ '<td align="left" class="card">' + msg["Name" + i]
						+ '</td>' + '<td align="left" class="card">'
						+ msg["Place" + i] + '</td>'
						+ '<td align="left" class="card">' + msg["Resname" + i]
						+ '</td>' + '</tr>');
			}
		}
	});
}
function point2(str) {
	var y1 = document.getElementById("mb");
	var y3 = document.getElementById("tbroomcost");
	y1.style.display = "block";
	y3.style.display = "block";
	var url = document.getElementById("path").value
			+ "/servlet/HYGS_DFPEServlet?idFromPage=" + str;
	$.ajax({
		type : "POST",
		async : true,
		url : url,
		Error : function(msg, status) {
		},
		success : function(msg) {
			console.log("shit", msg)
			// 请求成功,具体业务逻辑操作....
			var dom = $("#tbroomcost").find("tbody");
			for ( var i = 0; i < msg["size"]; i++) {
				dom.append('<tr>' + '<td align="left" class="card">'
						+ msg["Id" + i] + '</td>'
						+ '<td align="left" class="card">' + msg["Setype" + i]
						+ '</td>' + '<td align="left" class="card">'
						+ msg["Fitype" + i] + '</td>'
						+ '<td align="left" class="card">' + msg["Rotype" + i]
						+ '</td>' + '<td align="left" class="card">'
						+ msg["Roprice" + i] + '</td>'
						+ '<td align="left" class="card">'
						+ msg["Stdiscount" + i] + '</td>'
						+ '<td align="left" class="card">' + msg["Name" + i]
						+ '</td>' + '<td align="left" class="card">'
						+ msg["Place" + i] + '</td>'
						+ '<td align="left" class="card">' + msg["Resname" + i]
						+ '</td>' + '</tr>');
			}
		}
	});
}
function quit() {
	var q1 = document.getElementById("mb");
	var q2 = document.getElementById("tbhycard");
	var q3 = document.getElementById("tbhycost");
	var q4 = document.getElementById("tbroomcost");
	q1.style.display = "none";
	q2.style.display = "none";
	q3.style.display = "none";
	q4.style.display = "none";
	// var dom =
	$("#tbhycard").find("tbody").empty();
	$("#tbhycost").find("tbody").empty();
	$("#tbroomcost").find("tbody").empty();
}
