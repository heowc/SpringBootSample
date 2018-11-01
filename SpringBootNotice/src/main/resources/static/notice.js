/**
 * 게시판 스크립트
 */
$(function() {
    var INIT_PAGE_NUMBER = 1;

	var getNotice = function(pageNo) {
		$.ajax({
			url	 : '/notice?page='+pageNo,
			type : 'GET',
			success : function(response) {
				renderNoticeHtml(response.notices);
				renderPagingHtml(response.page);
			},
			error : function() {
				console.log('error');
			}
		})
	};
	
	var renderNoticeHtml = function(notices) {
		var notice_html = '';
		$(notices).each(function(key, value) {
			notice_html += '<tr>';
			notice_html += '<td>' + value.idx + '</td>';
			notice_html += '<td>' + value.title + '</td>';
			notice_html += '<td>' + value.content + '</td>';
			notice_html += '</tr>';
		});
		$('#notice-tbody').html(notice_html);
	};
	
	var renderPagingHtml = function(page) {
		$('#notice-paging').bootpag({
			total : page.totalSize / page.recodeSize,
			maxVisible : 10,
			href : '#'
		});
	};
	
	$('#notice-paging')
	.on('click' ,'li', function() {
		getNotice($(this).attr('data-lp'));
	});
	
	getNotice(INIT_PAGE_NUMBER);
});
