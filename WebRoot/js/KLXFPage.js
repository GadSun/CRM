//文本框能否输入-----------------------------------------------------------------------------
function oper() {
	var c1 = document.getElementById("chkpinyin");
	if (c1.checked) {
		document.getElementById("txpinyin").disabled = false;
	} else {
		document.getElementById("txpinyin").disabled = true;
		document.getElementById("txpinyin").value = "";
	}
}
function oper1() {
	var c2 = document.getElementById("chktotal_cost");
	if (c2.checked) {
		document.getElementById("txl_total_cost").disabled = false;
		document.getElementById("txr_total_cost").disabled = false;
	} else {
		document.getElementById("txl_total_cost").disabled = true;
		document.getElementById("txr_total_cost").disabled = true;
		document.getElementById("txl_total_cost").value = "";
		document.getElementById("txr_total_cost").value = "";
	}
}
function oper2() {
	var c3 = document.getElementById("chkdate_cost");
	if (c3.checked) {
		document.getElementById("txl_date_cost").disabled = false;
		document.getElementById("txr_date_cost").disabled = false;
	} else {
		document.getElementById("txl_date_cost").disabled = true;
		document.getElementById("txr_date_cost").disabled = true;
		document.getElementById("txl_date_cost").value = "";
		document.getElementById("txr_date_cost").value = "";
	}
}
function oper3() {
	var c4 = document.getElementById("chksaler");
	if (c4.checked) {
		document.getElementById("slcsaler").disabled = false;
	} else {
		document.getElementById("slcsaler").disabled = true;
	}
}
function oper4() {
	var c5 = document.getElementById("chklplace_cost");
	if (c5.checked) {
		document.getElementById("slclplace_cost").disabled = false;
	} else {
		document.getElementById("slclplace_cost").disabled = true;
	}
}
function oper5() {
	var c6 = document.getElementById("chkrplace_cost");
	if (c6.checked) {
		document.getElementById("slcrplace_cost").disabled = false;
	} else {
		document.getElementById("slcrplace_cost").disabled = true;
	}
}

// 点击查询按钮-----------------------------------------------------------------------------
function select() {
	// 清除复选框对号-----------------------------------------------------------------------------
	var test = document.getElementById("txpinyin");
	var test1 = document.getElementById("txl_total_cost");
	var test2 = document.getElementById("txr_total_cost");
	var test3 = document.getElementById("txl_date_cost");
	var test4 = document.getElementById("txr_date_cost");
	var c1 = document.getElementById("chkpinyin");
	var c2 = document.getElementById("chktotal_cost");
	var c3 = document.getElementById("chkdate_cost");
	if (test.value == "") {
		c1.checked = false;
		document.getElementById("txpinyin").disabled = true;
	}
	if (test1.value == "" && test2.value == "") {
		c2.checked = false;
		document.getElementById("txl_total_cost").disabled = true;
		document.getElementById("txr_total_cost").disabled = true;
	}
	if (test3.value == "" && test4.value == "") {
		c3.checked = false;
		document.getElementById("txl_date_cost").disabled = true;
		document.getElementById("txr_date_cost").disabled = true;
	}
	// 页面跳转-----------------------------------------------------------------------------

	form1.action = document.getElementById("path").value
			+ "/servlet/KLXFServlet";
	form1.submit();
}
//点击清除按钮---------------------------------------------------------------------------------
function pageClear() {
	form1.action = document.getElementById("path").value + "/servlet/KLXFUIServlet";
	form1.submit();
}
//分点击导出Excel表按钮---------------------------------------------------------------------------------
function getExcel(){
	var url = document.getElementById("path").value
	+ "/servlet/KLXFExcelServlet";
	console.log("1");
	$.ajax({
        type: "POST",
        async: true,
        url: url,
        Error: function(msg, status) {
        },
        success: function(msg) {
            //请求成功,具体业务逻辑操作....
        	if(msg["message"] != null){
        		alert("导出失败:1.请先点击查询按钮\n2.可能另一个程序正在使用此文件，进程无法访问");
        	}
        }
    });
}
//分点击打印预览按钮---------------------------------------------------------------------------------
function getPrint(){
	form1.action = document.getElementById("path").value
	+ "/servlet/KLXFPrintServlet";
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
// --------------------------------------------------------------------------------------------
