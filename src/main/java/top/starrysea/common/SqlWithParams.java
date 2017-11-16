package top.starrysea.common;

public class SqlWithParams {

	private String where;// where子句
	private String order;// order by子句
	private Object[] params;

	public SqlWithParams() {
	}

	public SqlWithParams(String where, Object[] params) {
		this.where = where;
		this.params = params;
	}

	public SqlWithParams(String where, String order, Object[] params) {
		this.where = where;
		this.order = order;
		this.params = params;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
