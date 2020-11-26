<template>
  <div class="match" align="center">
    <div id="nav">
      <router-link to="/">Home</router-link>
    </div>
    <MatchDetails/>
  </div>
</template>

<script>
// @ is an alias to /src
import MatchDetails from '@/components/MatchDetails.vue'

export default {
  name: 'Match',
  components: {
    MatchDetails
  },

}
</script>

<style>
#nav {
  padding: 10px;
}

</style>