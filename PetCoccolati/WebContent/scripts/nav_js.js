$(document).ready(function(){
  //Code so that if a li has the class active and the submenu is visible, it slides up, and vice versa. Also shows that if li doesnt has class active it will switch to that li clicked and slideup an visible submenu and dropdow the one under the li clicked
  $("#topnav a").click(function(){
    var el = $(this).parent();
    if(el.hasClass('active') &&      $(this).next().is(':visible')){
      var active = el.siblings('.active');
      $(this).next().slideUp();
    }
    else if(el.hasClass('active') &&      !$(this).next().is(':visible')){
      var active = el.siblings('.active');
      $(this).next().slideDown();
    }
    else if(!el.hasClass('active')){
      $(this).next().slideDown();
      var active = el.siblings('.active');
      active.children('ul:first').slideUp();
      active.removeClass('active');
      el.addClass('active');
    }
});
});