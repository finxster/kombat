$(function(){

    var body = $('body')
        , form = body.find('#delete-form');

    body.find('.form-delete').on('click', function( e ) {
        e.preventDefault();
        form.attr('action', e.target.href)[0].submit();
    });

});