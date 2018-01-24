
    // A (acts like a queue)
    this.contents.add(0, t); // push
    return this.contents.remove(this.contents.size() - 1); // pop

    // B (correct, acts like a stack!)
    this.contents.add(0, t); // push
    return this.contents.remove(0); // pop

    // C (acts like queue [off by one])
    this.contents.add(this.contents.size() - 1, t); // push
    return this.contents.remove(0); // pop

    // D (off by one, but right idea)
    this.contents.add(this.contents.size() - 1, t); // push
    return this.contents.remove(this.contents.size() - 1); // pop

