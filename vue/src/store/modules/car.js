import { Message } from 'element-ui'
// 购物车
const state = {
  goods: [],
  goodsNum: 0
}

const mutations = {
  //重新设置购物车
  setGoods(state, car) {
    state.goods = car
    localStorage.setItem("car", JSON.stringify(car));
    this.commit('setGoodsNum')
  },

  //设置购物车数量
  setGoodsNum(state) {
    if (!state.goods || state.goods.length == 0) {
      state.goodsNum = 0
    } else {
      state.goodsNum = state.goods.reduce(function (total, currentValue, currentIndex, arr) {
        return total + currentValue.buynum;
      }, 0);
    }
  },

  //加入购物车
  add(state, goodinfo) {
    if (goodinfo.num == 0) {
      Message({
        type: "error", message: "库存不足,添加失败"
      })
      return
    }

    let car = localStorage.getItem("car");
    if (!car) {
      //购物车不存在商品
      goodinfo.buynum = 1;
      car = [goodinfo];
      this.commit('setGoods', car)
      Message({
        type: "success", message: "添加到购物车成功"
      })
    } else {
      car = JSON.parse(car)
      //购物车存在商品
      try {
        let is = false;
        car.forEach((item, index) => {
          if (
            item.id == goodinfo.id &&
            Number(item.buynum) + 1 > Number(item.num)
          ) {
            throw new Error();
          }
          if (item.id == goodinfo.id) {
            is = true;
            car[index].buynum = Number(item.buynum) + 1;
          }
        });

        if (!is) {
          //新增
          goodinfo.buynum = 1;
          car = [...car, goodinfo];
        } else {
          //加数量
          car = [...car];
        }
        this.commit('setGoods', car)
        Message({
          type: "success", message: "添加到购物车成功"
        })
      } catch (error) {
        Message({
          type: "error", message: "已超过该商品可购买的最大值"
        })
        return;
      }
    }
  },
}

const actions = {

}

export default {
  state,
  mutations,
  actions
}
