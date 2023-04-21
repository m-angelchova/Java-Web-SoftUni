let loadBooksBtn = document.getElementById("loadBooks");

loadBooksBtn.addEventListener('click', reloadBooks)

function reloadBooks(){
 //   window.alert('RELOAD BOOKS CLICK')

    let booksContr = document.getElementById('books-container')
    booksContr.innerHTML = ''


    fetch("http://localhost:8080/api/books").
        then(response => response.json()).
        then(json => json.forEach(book => {
            let row = document.createElement('tr')

            let titleC= document.createElement('td')
            let authorC= document.createElement('td')
            let isbnC= document.createElement('td')
            let actionC= document.createElement('td')

            titleC.textContent = book.title
            authorC.textContent = book.author.name
            isbnC.textContent = book.isbn

            //actions
            let deleteBtn = document.createElement('button')
            deleteBtn.innerText = 'DELETE'
            deleteBtn.dataset.id = book.id
            deleteBtn.addEventListener('click', deleteBtnClicked)
            actionC.appendChild(deleteBtn)


            row.appendChild(titleC)
            row.appendChild(authorC)
            row.appendChild(isbnC)
            row.appendChild(actionC)

        booksContr.appendChild(row)
        }))

}
function deleteBtnClicked(event){
    let bookId = event.target.dataset.id;
    deleteBook(bookId);
}


function deleteBook (bookId){
    var requestOption = {
        method: 'DELETE'
    }

    fetch(`http://localhost:8080/api/books/${bookId}`, requestOption).
        then(_ => reloadBooks()).
        catch(error => console.log('error', error))
}