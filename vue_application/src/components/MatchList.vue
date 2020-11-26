<template>
  <div class="matchlist"  style="max-width: 65%;">
    <b-table-simple bordered small v-for="item in matches" :key="item.id">
      <b-thead head-variant="dark">
        <b-tr align="center">
          <b-th colspan="5">{{item.host}} - {{item.guest}}</b-th>
        </b-tr>
      </b-thead>
      <b-tbody>
        <b-tr :variant="item.finished ? item.hostPoints > item.guestPoints ? 'success': '': ''">
          <b-th colspan="4">{{item.host}}</b-th>
          <b-th colspan="1">{{item.hostPoints}}</b-th>
        </b-tr>
        <b-tr :variant="item.finished ? item.hostPoints < item.guestPoints ? 'success': '': ''">
          <b-th colspan="4">{{item.guest}}</b-th>
          <b-th colspan="1">{{item.guestPoints}}</b-th>
        </b-tr>
        <b-tr  align="center">
          <b-th colspan="5"><router-link :to="'/match/' + item.id">More details</router-link></b-th>
        </b-tr>
      </b-tbody>
    </b-table-simple>
  </div>
</template>

<script>
// @ is an alias to /src
import { mapState, mapActions } from 'vuex';

export default {
  name: 'MatchList',
  components: {

  },
  computed: {
    ...mapState(['matches'])
  },
  methods: {
    ...mapActions(['load_matches']),

  },
  mounted: function() {
    this.load_matches();
  },
}
</script>
