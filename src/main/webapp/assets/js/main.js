$(function(){

    var body = $('body')
        , filter = body.find('#filter-form')
        , del = body.find('#delete-form');

    body.find('.form-delete').on('click', function( e ) {
        e.preventDefault();
        return del.attr('action', e.target.href)[0].submit();
    });

    body.find('.order-field').on('click', function( e ){
        e.preventDefault();
        var el = $(this)
            , field = el.attr('rel')
            , order = filter.find('input[name=order]')
            , page = filter.find('input[name=page]')
            , asc = filter.find('input[name=asc]');

        el[ asc ? 'removeClass' : 'addClass' ]('desc');
        asc.val( field === order.val() ? !(asc.val() === 'true') : true );
        order.val( field );
        page.val( '1' );
        return filter[0].submit();
    });

});