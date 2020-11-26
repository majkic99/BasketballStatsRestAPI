<template>
  <div class="matchdetails">
    <div style="max-width: 65%; padding:10px;">
      <b-card>
        <b-card-title>
          {{matches.host.name}} {{matches.hostPoints}} : {{matches.guestPoints}} {{matches.guest.name}}
        </b-card-title>
        <b-card-sub-title>
          {{matches.finished ? 'FINISHED' : 'IN PROGRESS'}}
        </b-card-sub-title>
      </b-card>
    </div>
    <div style="max-width: 65%;">
      <b-table-simple bordered small>
        <b-thead head-variant="dark">
          <b-tr align="left">
            <b-th colspan="5">{{matches.host.name}}, {{matches.host.city}} {{matches.hostPoints}}</b-th>
          </b-tr>
        </b-thead>
        <b-tbody>
          <b-tr variant="primary">
            <b-th colspan="1">Full name</b-th>
            <b-th colspan="1">Position</b-th>
            <b-th colspan="1">Points</b-th>
            <b-th colspan="1">Assists</b-th>
            <b-th colspan="1">Rebounds</b-th>
          </b-tr>
          <b-tr v-for="player in matches.host.playerList">
            <b-th colspan="1">{{player.firstName}} {{player.lastName}} </b-th>
            <b-th colspan="1">{{player.position.replace("_", " ")}}</b-th>
            <b-th> <div v-for="(value, key) in matches.pointsMap"><b-th v-if="key == player.id" colspan="1">{{value}}</b-th></div></b-th>
            <b-th><div v-for="(value, key) in matches.assistsMap"><b-th v-if="key == player.id" colspan="1">{{value}}</b-th></div></b-th>
            <b-th><div v-for="(value, key) in matches.jumpsMap"><b-th v-if="key == player.id" colspan="1">{{value}}</b-th></div></b-th>
          </b-tr>
        </b-tbody>
      </b-table-simple>
      <b-table-simple bordered small>
        <b-thead head-variant="dark">
          <b-tr align="left">
            <b-th colspan="5">{{matches.guest.name}}, {{matches.guest.city}} {{matches.guestPoints}}</b-th>
          </b-tr>
        </b-thead>
        <b-tbody>
          <b-tr variant="primary">
            <b-th colspan="1">Full name</b-th>
            <b-th colspan="1">Position</b-th>
            <b-th colspan="1">Points</b-th>
            <b-th colspan="1">Assists</b-th>
            <b-th colspan="1">Rebounds</b-th>
          </b-tr>
          <b-tr v-for="player in matches.guest.playerList">
            <b-th colspan="1">{{player.firstName}} {{player.lastName}}</b-th>
            <b-th colspan="1">{{player.position.replace("_", " ")}}</b-th>
            <b-th> <div v-for="(value, key) in matches.pointsMap"><b-th v-if="key == player.id" colspan="1">{{value}}</b-th></div></b-th>
            <b-th><div v-for="(value, key) in matches.assistsMap"><b-th v-if="key == player.id" colspan="1">{{value}}</b-th></div></b-th>
            <b-th><div v-for="(value, key) in matches.jumpsMap"><b-th v-if="key == player.id" colspan="1">{{value}}</b-th></div></b-th>


          </b-tr>
        </b-tbody>
      </b-table-simple>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import { mapState, mapActions } from 'vuex';

export default {
  name: 'MatchDetails',
  components: {

  },
  computed: {
    ...mapState(['matches'])
  },
  methods: {
    ...mapActions(['load_match']),
    findPoints: function(){

    }
  },
  mounted: function() {
    this.load_match(this.$route.params.id);
  }
}
</script>
