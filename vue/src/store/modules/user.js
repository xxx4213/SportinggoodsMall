// 账号
const state = {
  userinfo: {}
}

const mutations = {
  setUserInfo(state, userinfo) {
    localStorage.setItem('userinfo', JSON.stringify(userinfo))
    state.userinfo = userinfo
  }
}

const actions = {
  // toggleDevice({ commit }, device) {
  //   commit('TOGGLE_DEVICE', device)
  // }
}

export default {
  state,
  mutations,
  actions
}
