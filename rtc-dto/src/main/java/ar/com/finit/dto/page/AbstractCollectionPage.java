package ar.com.finit.dto.page;

import java.util.Collection;
import java.util.HashSet;

/**
 * 
 * @author leo
 * 
 * @param <T>
 */
public abstract class AbstractCollectionPage<T> {

	protected long rowCount;
	protected int pageSize;
	protected int pageNumber;
	protected int pageCant;
	protected Collection<T> pageElements;

	public AbstractCollectionPage(long rowCount, int pageSize,int pageCant, int pageNumber, Collection<T> pageElements) {
		this.rowCount = rowCount;
		this.pageSize = pageSize;
		this.pageCant = pageCant;
		if (this.pageSize == -1) {
			this.pageNumber = 0;
		} else {
			this.pageNumber = pageNumber;
		}
		this.pageElements = pageElements;
	}

	public long getRowCount() {
		return this.rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	

	public int getPageCant() {
		return pageCant;
	}

	public void setPageCant(int pageCant) {
		this.pageCant = pageCant;
	}

	public Collection<T> getPageElements() {
		if (this.pageElements == null) {
			this.pageElements = new HashSet<T>();
		}
		return this.pageElements;
	}

	public void setPageElements(Collection<T> pageElements) {
		this.pageElements = pageElements;
	}

	public void addPageElement(T t) {
		this.getPageElements().add(t);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!this.isSameInstant(obj)) {
			return false;
		}
		@SuppressWarnings("rawtypes")
		AbstractCollectionPage other = (AbstractCollectionPage) obj;
		if (!((other.getPageNumber() == this.getPageNumber()) && (other.getPageSize() == this.getPageSize()) && (other.getRowCount() == this.getRowCount()))) {
			return false;
		}
		for (Object dto : other.getPageElements()) {
			if (!this.getPageElements().contains(dto)) {
				return false;
			}
		}
		return true;
	}

	protected abstract boolean isSameInstant(Object obj);
}