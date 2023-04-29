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
    icon: 'fad fa-coffee-togo',
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
        routeLink: 'staff/group',
        label: 'Nhóm nhân viên',
      },

    ],
  },
  {
    routeLink: 'profile',
    icon: 'fad fa-address-card',
    label: 'Tài khoản',
  },
  {
    routeLink: 'salary',
    icon: 'fa-solid fa-sack-dollar',
    label: 'Quỹ lương',
    items: [
      {
        routeLink: 'salary/create',
        label: 'Thêm mức lương',
      },
      {
        routeLink: 'salary/list',
        label: 'Danh sách mức lương',
      },
    ],
  },
  {
    routeLink: 'timekeeping',
    icon: 'fa-solid fa-share-from-square',
    label: 'Chấm công   ',
    items: [
      {
        routeLink: 'timekeeping/create',
        label: 'Thêm bảng chấm công',
      },
      {
        routeLink: 'timekeeping/list',
        label: 'Danh sách chấm công',
      },

    ],
  },
  // {
  //   routeLink: 'address',
  //   icon: 'fad fa-map-marker-alt',
  //   label: 'Địa chỉ',
  //   items: [
  //     {
  //       routeLink: 'address/map',
  //       label: 'Tấc cả chi nhánh',
  //     },
  //     {
  //       routeLink: 'address/create',
  //       label: 'Thêm chi nhánh mới',
  //     },
  //   ],
  // },
];

