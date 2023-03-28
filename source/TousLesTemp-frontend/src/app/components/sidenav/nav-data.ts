import { INavbarData } from "./helper"

export const navbarData: INavbarData[] = [
  {
    routeLink: 'stores',
    icon: 'fad fa-store',
    label: 'Cửa hàng',
    items: [
      {
        routeLink: 'stores/list',
        label: 'Thêm cửa hàng mới'
      },
      {
        routeLink: 'stores/stores',
        label: 'Danh sách cửa hàng'
      }, {
        routeLink: 'stores/list',
        label: 'Thêm cửa hàng mới'
      },
    ]
  },
  {
    routeLink: 'products',
    icon: 'fad fa-coffee-togo',//fal fa-box-open
    label: 'Sản phẩm',
    items: [
      {
        routeLink: 'products/list',
        label: 'Tấc cả sản phẩm'
      },
      {
        routeLink: 'products/create',
        label: 'Thêm mới'
      }, {
        routeLink: 'products/sell',
        label: 'Sản phẩm bán chạy'
      },
    ]
  },
  {
    routeLink: 'categories',
    // icon: 'fal fa-chart-bar',fad.fa-mug-tea,fa-coffee-togo
    icon: 'fad fa-mug-tea',
    label: 'Danh mục ',
    items: [
      {
        routeLink: 'categories/list',
        label: 'Loại sản phẩm'
      },
      {
        routeLink: 'categories/create',
        label: 'Thêm mới'
      }, {
        routeLink: 'categories/update',
        label: 'Cập nhật'
      },
    ]

  },
  {
    routeLink: 'stafff',
    icon: 'fad fa-users-cog',
    label: 'Nhân viên',
    items: [
      {
        routeLink: 'stafff/list',
        label: 'Thông tin nhân viên'
      },
      {
        routeLink: 'stafff/role',
        label: 'Vị trí'
      }, {
        routeLink: 'stafff/create',
        label: 'Thêm mới'
      },
    ]
  },

  {
    routeLink: 'report',
    icon: 'fal fa-file-export',
    label: 'Thống kê'
  },
  {
    routeLink: 'account',
    icon: 'fad fa-map-marker-alt',
    label: 'Tài khoản'
  },
  // {
  //   routeLink: 'account',
  //   icon: 'fad fa-clipboard-list-check',
  //   label: 'Tài khoản'
  // },
  {
    routeLink: 'address',
    icon: 'fad fa-address-card',
    label: 'Địa chỉ',
    items: [
      {
        routeLink: 'address/list',
        label: 'Thông tin chi tiết'
      },
      {
        routeLink: 'address/create',
        label: 'Thêm địa chỉ mới'
      },
    ]
  },
];
