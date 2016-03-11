/**
 * Created by VARSHA-PC on 2/19/2016.
 */
$( document ).ready(function() {
    var userInput = $('#username-input'),
        userWrap = $('.username-wrap'),
        user = $('#name');

    userInput.keydown(function (event) {
        if (event.keyCode == 13) {
            var name = $(this).val();
            user.text(name);
            userInput.addClass('hidden');
            userWrap.addClass('hidden');
            user.removeClass('hidden');
            user.parent().addClass('pw-active');
            $('.password').focus();
            return false;
        }
    });

    user.click(function (event) {
        user.addClass('hidden');
        user.parent().removeClass('pw-active');
        userInput.removeClass('hidden');
        userWrap.removeClass('hidden');
    });
});