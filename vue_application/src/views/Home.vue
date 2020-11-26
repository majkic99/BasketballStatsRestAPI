<template>
  <div class="home" align="center">
    <div>
      <b-jumbotron bg-variant="dark" text-variant="white" border-variant="dark" header="Basketball stats" lead="By Sanja organizator">
      </b-jumbotron>
    </div>
    <MatchList/>
  </div>
</template>

<script>
// @ is an alias to /src
import MatchList from '@/components/MatchList.vue'

export default {
  name: 'Home',
  components: {
    MatchList
  }
}
</script>
