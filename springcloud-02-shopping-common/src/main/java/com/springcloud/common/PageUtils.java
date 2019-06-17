package com.springcloud.common;

/**
 * 鍒嗛〉鐨勫伐鍏风被
 * 
 * @author A
 *
 */
public class PageUtils {

	/**
	 * 璁剧疆姣忛〉鏄剧ず鏁版嵁鐨勮鏁�
	 */
	public static final Integer PAGE_ROW_COUNT = 10;

	/**
	 * 椤电爜
	 */
	private Integer pageNumber;

	/**
	 * 璧峰琛�
	 */
	//private Integer startRow;

	/**
	 * 鏈�鍚庝竴椤电殑椤电爜
	 */
	private Integer lastPage;

	/**
	 * 鍒嗛〉鎸夐挳淇℃伅
	 */
	private int[] pageButton;

	private Integer pageRowCount;

	public PageUtils(Integer rowCount) {
		this(PAGE_ROW_COUNT, rowCount);
	}

	public PageUtils(Integer pageRowCount, Integer rowCount) {
		this.pageRowCount = pageRowCount;
		// 璁＄畻鏈�鍚庝竴椤电殑椤电爜
		this.lastPage = (rowCount + pageRowCount - 1) / pageRowCount;
	}

	public void setPageNumber(Integer pageNumber) {
		if (pageNumber > this.lastPage) {
			this.pageNumber = this.lastPage;
			return;
		}
		this.pageNumber = pageNumber;
	}

	public Integer getPageRowCount() {
		return pageRowCount;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	/*public Integer getStartRow() {
		this.startRow = pageNumber * pageRowCount - pageRowCount;
		return startRow;
	}*/

	public Integer getLastPage() {
		return lastPage;
	}

	public int[] getPageButton() {
		// 鍒嗛〉鎸夐挳鐨勮捣濮嬫寜閽�
		int startPage;
		// 鍒嗛〉鎸夐挳鐨勭粨鏉熸寜閽�
		int endPage;

		if (this.lastPage <= 4) {
			// 濡傛灉鏈�鍚庝竴椤靛皬浜�4
			startPage = 1;
			endPage = this.lastPage;
		} else {
			if (this.pageNumber < 4) {
				// 濡傛灉褰撳墠椤电爜灏忎簬4
				startPage = 1;
				endPage = 4;
			} else {
				startPage = this.pageNumber - 1;
				endPage = this.pageNumber + 2 <= this.lastPage ? this.pageNumber + 2 : this.lastPage;
				if ((endPage - startPage) <= 2) {
					startPage = endPage - 3;
				}
			}
		}
		this.pageButton = new int[endPage - (startPage - 1)];
		for (int i = 0; i < pageButton.length; i++) {
			pageButton[i] = startPage++;
		}
		return this.pageButton;
	}

}
