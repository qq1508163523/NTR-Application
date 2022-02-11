<template>
  <div class="player-container">
    <div id="dplayer">
    </div>
  </div>
</template>

<script>

import DPlayer from 'dplayer';

export default {
  name: "Player",
  props: ['branchId','episodeNumber']
}
</script>

<style lang="scss" scoped>

</style>