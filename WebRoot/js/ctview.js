//分页管理---------------------------------------------------------------------------------
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
	this.__updateTableRows__();
	var dom = $("#main").find("#pp");
	dom.append("当前页数：" + (this.pageIndex + 1));
};
/*
 * 下一页
 */
Page.prototype.nextPage = function() {
	var dom = $("#main").find("#pp");
	dom.empty();
	dom.append("当前页数：");
	if (this.pageIndex + 1 < this.pageCount) {
		this.pageIndex += 1;
		this.__updateTableRows__();
	}
	dom.append((this.pageIndex+1));
};
/*
 * 上一页
 */
Page.prototype.prePage = function() {
	var dom = $("#main").find("#pp");
	dom.empty();
	dom.append("当前页数：");
	if (this.pageIndex >= 1) {
		this.pageIndex -= 1;
		this.__updateTableRows__();
	}
	dom.append((this.pageIndex+1));
};
/*
 * 首页
 */
Page.prototype.firstPage = function() {
	var dom = $("#main").find("#pp");
	dom.empty();
	dom.append("当前页数：");
	if (this.pageIndex != 0) {
		this.pageIndex = 0;
		this.__updateTableRows__();
	}
	dom.append(1);
};
/*
 * 尾页
 */
Page.prototype.lastPage = function() {
	var dom = $("#main").find("#pp");
	dom.empty();
	dom.append("当前页数：");
	if (this.pageIndex + 1 != this.pageCount) {
		this.pageIndex = this.pageCount - 1;
		this.__updateTableRows__();
	}
	dom.append(this.pageCount);
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