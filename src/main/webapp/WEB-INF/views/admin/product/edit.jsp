<%@page import="com.i8web.model.Admin.PostsModel"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/views/admin/layout/header.jsp"%>
<div id="main-content-wp" class="add-cat-page">
	<div class="wrap clearfix">
		<%@include file="/WEB-INF/views/admin/layout/sidebar.jsp"%>
		<div id="content" class="fl-right">
			<div class="section" id="title-page">
				<div class="clearfix">
					<h3 id="index" class="fl-left">Cập nhật sản phẩm</h3>
				</div>
			</div>
			<div class="section" id="detail-page">
				<div class="section-detail">
					<c:forEach var="item" items="${ ProductId }">
						<form:form action="" method="POST" modelAttribute="product">
							<label for="title">Tên sản phẩm</label>
							<form:input type="text" value="${item.name }" name="name" id="name"
								path="name" />
							<label for="price_old">Giá cũ</label>
							<form:input type="text" value="${item.price_old }" name="price_old"
								id="price_old" path="price_old" />
							<label for="price_new">Giá mới</label>
							<form:input type="text" value="${item.price_new }" name="price_new"
								id="price_new" path="price_new" />
							<label for="desc">Mô tả</label>
							<form:textarea value="${item.description }" name="desc" id="desc"
								class="ckeditor" path="description"></form:textarea>
							<label for="status">Status</label>
							<form:input type="text" value="${item.status }" name="status" id="status"
								path="status" />
							<label>Hình ảnh</label>
							<div id="uploadFile">
								<form:input type="file" value="${item.image }" name="file"
									id="upload-thumb" path="image" />
							</div>
							<label for="detail">Chi tiết sản phẩm</label>
							<form:input type="text" value="${item.detail }" name="detail" id="detail"
								path="detail" />
							<label for="ghimSale">ghimSale</label>
							<form:input type="text" value="${item.ghimSale }" name="ghimSale"
								id="ghimSale" path="ghimSale" />
							<label for="ghimNew">ghimNew</label>
							<form:input type="text" value="${item.ghimNew }" name="ghimNew" id="ghimNew"
								path="ghimNew" />
							<label for="title">Slug ( Friendly_url )</label>
							<form:input type="text" value="${item.slug }" name="slug" id="slug"
								path="slug" />
							<label>Danh mục cha</label>
							<form:select name="parent-Cat" value="${item.product_id }"
								path="product_id">
								<option value="">-- Chọn danh mục --</option>
								<option value="1">Thể thao</option>
								<option value="2">Xã hội</option>
								<option value="3">Tài chính</option>
							</form:select>
							<div>
								<button type="submit" name="btn-submit" id="btn-submit">Cập
									nhật</button>
							</div>
						</form:form>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/views/admin/layout/footer.jsp"%>