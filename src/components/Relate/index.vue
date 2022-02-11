<template>
  <div class="relate-container" v-if="relateList.length">
    <div class="icon-title">
      <span style="font-size: 1.25rem;">相关动画</span>
    </div>
    <div>
      <ItemDisplay :itemList="relateList" :limitSize="999" :gutter="6" :colSize="6"/>
    </div>
  </div>
</template>

<script>

import ItemDisplay from "@/components/ItemDisplay";

export default {
  name: "Relate",
  props: ['relateList'],
  components: {
    ItemDisplay
  }
}
</script>

<style lang="scss" scoped>


</style>