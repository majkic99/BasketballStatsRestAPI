import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {

  	matches: []

  },
  mutations: {

  	set_matches: function(state, matches){
  		state.matches = matches;
  		
  	}

  },
  actions: {

  	load_matches: function ({ commit }) {
      fetch('http://localhost:8080/api/matches', { method: 'get' }).then((response) => {
        if (!response.ok)
          throw response;

        return response.json()
      }).then((jsonData) => {
        commit('set_matches', jsonData)
      }).catch((error) => {
        if (typeof error.text === 'function')
          error.text().then((errorMessage) => {
            alert(errorMessage);
          });
        else
          alert(error);
      });
    },
    load_match: function ({ commit }, id) {
      fetch(`http://localhost:8080/api/matches/${id}`, { method: 'get' }).then((response) => {
        if (!response.ok)
          throw response;

        return response.json()
      }).then((jsonData) => {
        commit('set_matches', jsonData)
      }).catch((error) => {
        if (typeof error.text === 'function')
          error.text().then((errorMessage) => {
            alert(errorMessage);
          });
        else
          alert(error);
      });
    }

  },
  modules: {
  }
})
