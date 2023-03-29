import { INavbarData } from './helper';

export const navbarData: INavbarData[] = [
  {
    routeLink: 'stores',
    icon: 'fad fa-store',
    label: 'Cửa hàng',
    items: [
      {
        routeLink: 'stores/create',
        label: 'Thêm cửa hàng mới',
      },
      {
        routeLink: 'stores/list',
        label: 'Danh sách cửa hàng',
      },
      {
        routeLink: 'stores/stores',
        label: 'Thêm cửa hàng mới',
      },
    ],
  },
  {
    routeLink: 'products',
    icon: 'fad fa-coffee-togo', //fal fa-box-open
    label: 'Sản phẩm',
    items: [
      {
        routeLink: 'products/list',
        label: 'Tấc cả sản phẩm',
      },
      {
        routeLink: 'products/create',
        label: 'Thêm mới',
      },
      {
        routeLink: 'products/sell',
        label: 'Sản phẩm bán chạy',
      },
    ],
  },
  {
    routeLink: 'categories',

    icon: 'fa fa-layer-group',
    label: 'Danh mục ',
    items: [
      {
        routeLink: 'categories/list',
        label: 'Loại sản phẩm',
      },
      {
        routeLink: 'categories/create',
        label: 'Thêm mới',
      },
      {
        routeLink: 'categories/update',
        label: 'Cập nhật',
      },
    ],
  },
  {
    routeLink: 'stafff',
    icon: 'fad fa-users-cog',
    label: 'Nhân viên',
    items: [
      {
        routeLink: 'stafff/list',
        label: 'Thông tin nhân viên',
      },
      {
        routeLink: 'stafff/role',
        label: 'Vị trí',
      },
      {
        routeLink: 'stafff/create',
        label: 'Thêm mới',
      },
    ],
  },

  {
    routeLink: 'report',
    icon: 'fal fa-file-export',
    label: 'Thống kê',
  },
  {
    routeLink: 'account',
    icon: 'fad fa-address-card',
    label: 'Tài khoản',
  },

  {
    routeLink: 'address',
    icon: 'fad fa-map-marker-alt',
    label: 'Địa chỉ',
    items: [
      {
        routeLink: 'address/list',
        label: 'Thông tin chi tiết',
      },
      {
        routeLink: 'address/create',
        label: 'Thêm địa chỉ mới',
      },
    ],
  },
];

// {
//   routeLink: 'account',
//   icon: 'fad fa-clipboard-list-check',
//   label: 'Tài khoản'
// },  // icon: 'fal fa-chart-bar',fad.fa-mug-tea,fa-coffee-togo, fa fa-mug-tea
