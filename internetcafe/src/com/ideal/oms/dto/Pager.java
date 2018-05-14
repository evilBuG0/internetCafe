package com.ideal.oms.dto;

/**
 * Created by dell on 14-11-6.
 */
import java.util.List;

public class Pager<T> {

	// 页面上显示的页号数量
	private int pageCount = 7;

	private int totalPage;

	private List<T> rows;

	private int current;

	private int begin;

	private int end;

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setBeginAndEnd() {
		if (totalPage == 0) {
			this.begin = 1;
			this.end = 1;
		} else if (totalPage <= pageCount) {
			this.begin = 1;
			this.end = totalPage;
		} else {
			if (current - 3 > 1) {
				if (3 + current < totalPage) {
					this.begin = current - 3;
					this.end = current + 3;
				} else {
					this.end = totalPage;
					this.begin = totalPage - pageCount;
				}
			} else {
				this.begin = 1;
				this.end = pageCount;
			}
		}
	}

}
