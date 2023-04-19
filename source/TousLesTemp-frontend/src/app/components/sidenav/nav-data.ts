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

    ],
  },
  {
    routeLink: 'products',
    icon: 'fad fa-coffee-togo', //fal fa-box-open
    label: 'Sản phẩm',
    items: [
      {
        routeLink: 'products/create',
        label: 'Thêm sản phẩm mới',
      },
      {
        routeLink: 'products/list',
        label: 'Danh sách sản phẩm',
      },
    ],
  },
  {
    routeLink: 'categories',

    icon: 'fa fa-layer-group',
    label: 'Danh mục ',
    items: [
      {
        routeLink: 'categories/create',
        label: 'Thêm loại mới',
      },
      {
        routeLink: 'categories/list',
        label: 'Danh sách loại',
      }
    ],
  },
  {
    routeLink: 'staff',
    icon: 'fad fa-users-cog',
    label: 'Nhân viên',
    items: [
      {
        routeLink: 'staff/create',
        label: 'Thêm nhân viên',
      },
      {
        routeLink: 'staff/list',
        label: 'Thông tin nhân viên',
      },
      {
        routeLink: 'staff/role',
        label: 'Nhóm nhân viên',
      },

    ],
  },

  // {
  //   routeLink: 'report',
  //   icon: 'fal fa-file-export',
  //   label: 'Thống kê',
  // },
  // {
  //   routeLink: 'account',
  //   icon: 'fad fa-address-card',
  //   label: 'Tài khoản',
  // },

  {
    routeLink: 'address',
    icon: 'fad fa-map-marker-alt',
    label: 'Địa chỉ',
    items: [
      {
        routeLink: 'address/map',
        label: 'Tấc cả chi nhánh',
      },
      {
        routeLink: 'address/create',
        label: 'Thêm chi nhánh mới',
      },
    ],
  },
];

// {
//   routeLink: 'account',
//   icon: 'fad fa-clipboard-list-check',
//   label: 'Tài khoản'
// },  // icon: 'fal fa-chart-bar',fad.fa-mug-tea,fa-coffee-togo, fa fa-mug-tea
