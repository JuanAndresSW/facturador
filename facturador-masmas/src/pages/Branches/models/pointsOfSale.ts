export type pointsOfSale = {
    content: {
        pointOfSaleId: number,
        pointOfSaleNumber: number,
    }[],
    page: number,
    size: number,
    totalElements: number,
    totalPages: number,
    last: boolean
}