<g:if test="${flash.message}">
    <div class="alert alert-warning alert-dismissible show" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
        ${flash.message}
    </div>
</g:if>