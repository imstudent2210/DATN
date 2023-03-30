import { MatPaginatorIntl } from '@angular/material/paginator';


export function CustomPaginator() {
  const customPaginatorIntl = new MatPaginatorIntl();
  customPaginatorIntl.itemsPerPageLabel = 'Hiển thị';
  customPaginatorIntl.nextPageLabel = 'Trang tiếp theo';
  customPaginatorIntl.previousPageLabel = 'Trang trước';
  customPaginatorIntl.firstPageLabel = 'Trang đầu';
  customPaginatorIntl.lastPageLabel = 'Trang cuối';
  customPaginatorIntl.getRangeLabel = (page: number, pageSize: number, length: number) => {
    length = Math.max(length, 0);
    const startIndex = page * pageSize;
    const endIndex = startIndex < length ?
      Math.min(startIndex + pageSize, length) :
      startIndex + pageSize;
    return startIndex + 1 + ' - ' + endIndex + ' | Tổng: ' + length;
  }

  return customPaginatorIntl;
}
