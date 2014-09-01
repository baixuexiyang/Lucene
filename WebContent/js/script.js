$(function() {
	$('#search').on('click', function() {
		if(!$('#kw').val())
			return false;
		var len = $('.condition ul li input:checkbox:checked').length;
		if(len === 0) {
			alert('请选择搜索条件');
			return false;
		}
	});

	var str = '';
	$('.condition ul li input:checkbox:checked').each(function() {
		str += $(this).prev('label').text() + '、';
	});
	str = str.substr(0, str.lastIndexOf('、'));
	$('.search-key').text(str);
});