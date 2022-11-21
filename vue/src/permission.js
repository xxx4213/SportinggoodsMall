import router from './router'


const whiteList = ['/login'] // no redirect whitelist

router.beforeEach(async (to, from, next) => {
  // start progress bar
  let islogin = sessionStorage.getItem('islogin')

  if (to.path.includes('/admin') || to.path == "/login") {
    console.log('后台');
    if (islogin == null && to.path == '/login') {
      next()
    } else if (islogin == null && to.path !== '/login') {
      next('/login')
    } else if (islogin != null && to.path == '/login') {
      next('/admin')
    } else {
      next()
    }
  }
  // else {
  //   console.log('前台');
  //   if (islogin == null && to.path == '/userlogin') {
  //     next()
  //   } else if (islogin == null && to.path !== '/userlogin') {
  //     next('/userlogin')
  //   } else if (islogin != null && to.path == '/userlogin') {
  //     next('/home')
  //   } else {
  //     next()
  //   }
  // }
  next()

})

router.afterEach(() => {
})
