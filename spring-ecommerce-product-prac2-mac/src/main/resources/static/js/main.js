async function deleteProductById(id) {
    try {
        const response = await fetch(`/api/products/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
        })
        if (response.ok) {
            const element = document.getElementById(`${id}`)
            if (element) {
                element.remove()
                showNotification("ok, deleted", 'success')
            } else {
                showNotification("Not Found", 'error')
            }
        } else {
            showNotification("Product cannot be deleted", 'error')
        }
    } catch (error) {
        showNotification("Error occurred:", 'error')
    }
}

async function deleteAllProducts() {
    if (!confirm('Are you sure you want to delete ALL products?')) {
        return;
    }
    try {
        const response = await fetch(`/api/products`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
        })
        if (response.ok) {
            document.querySelectorAll('tr[id]').forEach(element => {
                element.remove();
            });
            showNotification("All products deleted", 'success');
        } else {
            showNotification("Failed to delete all products", 'error');
        }
    } catch (error) {
        showNotification("Error occurred while deleting products", 'error');
    }
}

async function addNewProduct() {
    const name = prompt('Enter product name:');
    const price = prompt('Enter product price:');
    const imageUrl = prompt('Enter product image URL:');
    
    if (!name || !price || !imageUrl) {
        showNotification('All fields are required', 'error');
        return;
    }
    try {
        const response = await fetch('/api/products', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                name: name,
                price: parseFloat(price),
                imageUrl: imageUrl
            })
        });
        if (response.ok) {
            showNotification('Product added successfully', 'success');
            location.reload();
        } else {
            showNotification('Failed to add product', 'error');
        }
    } catch (error) {
        showNotification('Error occurred while adding product', 'error');
    }
}

async function editProduct(id) {
    try {
        const getResponse = await fetch(`/api/products/${id}`);
        if (!getResponse.ok) {
            showNotification('Product not found', 'error');
            return;
        }
        const product = await getResponse.json();
        const name = prompt('Enter product name:', product.name);
        const price = prompt('Enter product price:', product.price);
        const imageUrl = prompt('Enter product image URL:', product.imageUrl);
        if (!name || !price || !imageUrl) {
            showNotification('All fields are required', 'error');
            return;
        }
        const updateResponse = await fetch(`/api/products/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                name: name,
                price: parseFloat(price),
                imageUrl: imageUrl
            })
        });
        if (updateResponse.ok) {
            showNotification('Product updated successfully', 'success');
            location.reload();
        } else {
            showNotification('Failed to update product', 'error');
        }
    } catch (error) {
        showNotification('Error occurred while updating product', 'error');
    }
}

function showNotification(message, type) {
    const notification = document.createElement('div')
    notification.className = `alert alert-${type === 'success' ? 'success' : 'danger'} alert-dismissible`
    notification.innerHTML = `${message} <button type="button" class="btn-close" data-bs-dismiss="alert"></button>`
    const closeButton = notification.querySelector('.btn-close')
    closeButton.addEventListener('click', () => {
        notification.remove()
    })
    document.querySelector('.container').prepend(notification)
    setTimeout(() => {
        if (notification.parentNode) {
            notification.remove()
        }
    }, 1000)
}