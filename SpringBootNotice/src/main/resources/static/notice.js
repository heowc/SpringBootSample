/**
 * 게시판 스크립트
 */
$(function() {
	var getNotice = function(pageNo) {
		$.ajax({
			url		: '/notice?page='+pageNo,
			type	: 'GET',
			success	: function(response) {
				console.log('success');
				noticeHtml(response.notices);
				pagingHtml(response.page);
			},
			error	: function() {
				console.log('error');
			}
		})
	};
	
	var noticeHtml = function(notices) {
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
	
	var pagingHtml = function(page) {
		var page_html = '';
		var pageSize = page.totalSize/page.recodeSize;
		
		if(page.pageNo == 1) {
			page_html += '<li class="pre disabled">';
			page_html += '<a href="#" aria-label="Peivious"><span aria-hidden="true">&laquo;</span></a>';
			page_html += '</li>';
		} else {
			page_html += '<li class="pre">';
			page_html += '<a href="#" aria-label="Peivious"><span aria-hidden="true">&laquo;</span></a>';
			page_html += '</li>';
		}
		
		for(var item = 1; item <= pageSize; item++) {
			if(page.pageNo == item) {
				page_html += '<li class="page active"><a href="#">' + item + '</a></li>';
			} else {
				page_html += '<li class="page"><a href="#">' + item + '</a></li>';
			}
		}
		
		if(page.pageNo == pageSize) {
			page_html += '<li class="next disabled">';
			page_html += '<a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>';
			page_html += '</li>';
		} else {
			page_html += '<li class="next">';
			page_html += '<a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>';
			page_html += '</li>';
		}
		
		$('#notice-paging').html(page_html);
	};
	
	$('#notice-paging').on('click', '.page a', function() {
		getNotice($(this).text());
	});
	
	$('#notice-paging').on('click', '.pre', function() {
		var page = Number($('#notice-paging').find('.active a').text());
		getNotice(--page);
	});
	
	$('#notice-paging').on('click', '.next', function() {
		var page = Number($('#notice-paging').find('.active a').text());
		getNotice(++page);
	});
	
	getNotice(1);
});
