$ ->
  $.get "/recos", (recos) ->
    $.each recos, (index, reco) ->
      $.each reco, (index, recoListElem) ->
        $('#first').append $("<li>").text recoListElem;