const getters = {
  userinfo: state => state.user.userinfo,
  goods: state => state.car.goods,
  goodsNum: state => state.car.goodsNum,
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name
}
export default getters
