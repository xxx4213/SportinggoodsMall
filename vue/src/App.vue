<template>
  <div id="app" class="bg">
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App"
};
</script>

<style lang="scss" scoped>
.bg {
  // background: url(~@/assets/3.jpg) no-repeat;
  background-size: 100% 100%;
}
</style>
