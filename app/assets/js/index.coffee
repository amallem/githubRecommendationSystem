$ ->
  $.get "/recos", (recos) ->
    $.each recos, (index, reco) ->
      $('#recos').append $("<li>").text reco